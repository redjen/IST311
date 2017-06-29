package shared;

/**
 *
 * @author maximdumont
 */
public interface Seedable {

    String generate(int seed);

    String generate();
}
