package us.looking_glass.util;

import java.util.Arrays;
import java.util.Random;

public final class Well1024a {
    protected int index = 0;
    protected final int[] v = new int[32];
    private static final int m1 = 3;
    private static final int m2 = 24;
    private static final int m3 = 10;

    public final void setSeed(int[] seed) {
        Arrays.fill(v, 0);
        int lim = Math.min(seed.length, 32);
        for (int i = 0; i < lim; i++)
            v[i] = seed[i];
    }

    private final int iRm1(int i) {
        return (i + 31) & 0x1f;
    }

    private final int i1(int i) {
        return (i + m1) & 0x1f;
    }

    private final int i2(int i) {
        return (i + m2) & 0x1f;
    }

    private final int i3(int i) {
        return (i + m3) & 0x1f;
    }

    public int next(final int bits) {
        final int indexRm1 = iRm1(index);
        final int v0 = v[index];
        final int vM1 = v[i1(index)];
        final int vM2 = v[i2(index)];
        final int vM3 = v[i3(index)];

        final int z0 = v[indexRm1];
        final int z1 = v0 ^ (vM1 ^ (vM1 >>> 8));
        final int z2 = (vM2 ^ (vM2 << 19)) ^ (vM3 ^(vM3 << 14));
        final int z3 = z1 ^ z2;
        final int z4 = (z0 ^ (z0 << 11)) ^ (z1 ^ (z1 << 7)) ^ (z2 ^ (z2 << 13));

        v[index] = z3;
        v[indexRm1] = z4;
        index = indexRm1;
        return z4 >>> (32 - bits);
    }

    public double nextDouble() {
        final long high = ((long) next(26)) << 26;
        final int low = next(26);
        return (high | low) * 0x1.0p-52d;
    }

    public int nextInt(int n) {
        if (n > 0) {
            if ((n & -n) == n) {
                return (int) ((n * (long) next(31)) >> 31);
            }
            int bits;
            int val;
            do {
                bits = next(31);
                val = bits % n;
            } while (bits - val + (n - 1) < 0);
            return val;
        }
        throw new IllegalArgumentException(String.format("Value not positive: %d", n));
    }

    public int nextInt(int m, int n) {
        return m + nextInt(n - m);
    }

    public float nextFloat() {
        return next(23) * 0x1.0p-23f;
    }
}
