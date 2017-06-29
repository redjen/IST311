package shared;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author maximdumont
 */
public class CryptoRandomSeed implements Seedable {

    private SecureRandom seedGenerator;
    private final int DEFAULT_SEED_LENGTH = 16;

    public CryptoRandomSeed(SecureRandom random) {
        this.seedGenerator = random;
    }

    public CryptoRandomSeed() {
        this(new SecureRandom());
    }

    public CryptoRandomSeed(byte[] seed) {
        this(new SecureRandom(seed));
    }

    @Override
    public String generate(int seed) {
        return Arrays.toString(seedGenerator.generateSeed(seed));
    }

    @Override
    public String generate() {
        return generate(DEFAULT_SEED_LENGTH);
    }

    public static String create(byte[] seed) {
        return new CryptoRandomSeed(seed).generate();
    }

    public static String create() {
        return new CryptoRandomSeed().generate();
    }
}
