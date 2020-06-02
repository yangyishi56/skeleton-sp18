package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int worldWidth;
    private int worldHeight;

    //calculate the size of the world
    public void calWorldSize(int size) {
        /*
        //single block
        this.worldWidth = size * 3  - 2;
        this.worldHeight = size * 2;
        */

        //block aggregation
        this.worldWidth = (size * 3  - 2) * 3 + size * 2;
        this.worldHeight = size * 2 * 5;
    }

    //initialize the tile rendering engine with a window of size WIDTH x HEIGHT
    public TERenderer renderEngine() {
        TERenderer ter = new TERenderer();
        ter.initialize(this.worldWidth, this.worldHeight);
        return ter;
    }

    /*
    Initiazlize the tiles
     */
    public TETile[][] initializeTiles (int size) {
        TETile[][] world = new TETile[this.worldWidth][this.worldHeight];
        for (int hi = 0; hi < this.worldHeight; hi += 1) {
            for (int wid = 0; wid < this.worldWidth; wid += 1) {
                world[wid][hi] = Tileset.NOTHING;
            }
        }

        int startPosWid = size * 3  - 2 + size;
        int startPosHi = 0;

        Random rand = new Random();

        fillSingleBlock(size, world, startPosWid, startPosHi, rand.nextInt(9));
        fillSingleBlock(size, world, startPosWid, startPosHi + 8 * size, rand.nextInt(9));

        startPosWid = size * 2 - 1;
        for (int i = 0; i < 2; i++) {
            startPosHi = size;
            fillSingleBlock(size, world, startPosWid, startPosHi, rand.nextInt(9));
            fillSingleBlock(size, world, startPosWid, startPosHi + 6 * size, rand.nextInt(9));
            startPosWid += size * 4  - 2;
        }

        startPosWid = 0;
        startPosHi = size * 2;
        for (int i = 0; i < 3; i++) {
            fillSingleBlock(size, world, startPosWid, startPosHi, rand.nextInt(9));
            fillSingleBlock(size, world, startPosWid + size * 4  - 2, startPosHi, rand.nextInt(9));
            fillSingleBlock(size, world, startPosWid + 2 * (size * 4  - 2), startPosHi, rand.nextInt(9));
            startPosHi += size * 2;
        }

        startPosWid = size * 2  - 1;
        startPosHi = size * 3;
        for (int i = 0; i < 2; i++) {
            fillSingleBlock(size, world, startPosWid, startPosHi, rand.nextInt(9));
            fillSingleBlock(size, world, startPosWid + size * 4  - 2, startPosHi, rand.nextInt(9));
            startPosHi += size * 2;
        }


        return world;
    }

    /**
     * filling a single block
     */
    public void fillSingleBlock(int size, TETile[][] world, int startPosWid, int startPosHi, int pat) {
        Map<Integer, TETile> pattern = new HashMap<>();
        pattern.put(0, Tileset.FLOWER);
        pattern.put(1, Tileset.MOUNTAIN);
        pattern.put(2, Tileset.SAND);
        pattern.put(3, Tileset.UNLOCKED_DOOR);
        pattern.put(4, Tileset.LOCKED_DOOR);
        pattern.put(5, Tileset. WATER);
        pattern.put(6, Tileset.GRASS);
        pattern.put(7, Tileset.FLOOR);
        pattern.put(8, Tileset.WALL);

        int offset = 0;
        for (int hi = size; hi < size * 2; hi += 1) {
            for (int wid = offset; wid < size * 3  - 2 - offset; wid++) {
                world[wid + startPosWid][hi + startPosHi] = pattern.get(pat);
            }
            offset++;
        }

        // fills in the lower part of the block
        offset = size - 1;
        for (int hi = 0; hi < size; hi += 1) {
            for (int wid = offset; wid < size * 3  - 2 - offset; wid++) {
                world[wid + startPosWid][hi + startPosHi] = pattern.get(pat);
            }
            offset--;
        }
    }

    // draws the world to the screen
    public void drawWorld(int size) {
        this.calWorldSize(size);
        TERenderer ter = renderEngine();
        TETile[][] world = initializeTiles (size);
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        //render a single hex providing size
        HexWorld single = new HexWorld();
        single.drawWorld(5);
    }
}
