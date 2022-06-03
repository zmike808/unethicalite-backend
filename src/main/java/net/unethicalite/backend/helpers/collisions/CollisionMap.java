package net.unethicalite.backend.helpers.collisions;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class CollisionMap {
    public final BitSet4D[] regions = new BitSet4D[256 * 256];

    public CollisionMap() {
    }

    public CollisionMap(byte[] data) {
        var buffer = ByteBuffer.wrap(data);

        while (buffer.hasRemaining()) {
            var region = buffer.getShort() & 0xffff;
            regions[region] = new BitSet4D(buffer, 64, 64, 4, 2);
        }
    }

    public byte[] toBytes() {
        var regionCount = (int) Arrays.stream(regions).filter(Objects::nonNull).count();
        var buffer = ByteBuffer.allocate(regionCount * (2 + 64 * 64 * 4 * 2 / 8));

        for (var i = 0; i < regions.length; i++) {
            if (regions[i] != null) {
                buffer.putShort((short) i);
                regions[i].write(buffer);
            }
        }

        return buffer.array();
    }

    public void set(int x, int y, int z, int w, boolean value) {
        var region = regions[x / 64 * 256 + y / 64];

        if (region == null) {
            return;
        }

        region.set(x % 64, y % 64, z, w, value);
    }

    public BitSet4D getRegion(int x, int y) {
        int regionId = x / 64 * 256 + y / 64;
        return regions[regionId];
    }

    public void createRegion(int region) {
        regions[region] = new BitSet4D(64, 64, 4, 2);
        regions[region].setAll(true);
    }

    public boolean get(int x, int y, int z, int w) {
        var region = getRegion(x, y);

        if (region == null) {
            return true;
        }

        int regionX = x % 64;
        int regionY = y % 64;

        return region.get(regionX, regionY, z, w);
    }
}
