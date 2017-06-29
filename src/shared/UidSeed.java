package shared;

import java.util.UUID;

/**
 *
 * @author maximdumont
 */
public class UidSeed implements Seedable {

    private UUID lastUuid;
    private int minimum = -1;
    private int maximum = -1;
    
    public UidSeed() {

    }
    
    public UidSeed(int min,int max){
        minimum = min;
        maximum = max;
    }

    @Override
    public String generate(int seed) {
        UUID uid;
        if(!(minimum==-1 || maximum == -1)){
            uid = new UUID(minimum, maximum);
        }else{
            uid = new UUID(seed, seed);
        }
        
        lastUuid = uid;
        return lastUuid.toString();
    }

    @Override
    public String generate() {
        lastUuid = UUID.randomUUID();
        return lastUuid.toString();
    }

    public UUID getLasrtUUID() {
        return lastUuid;
    }
    
    public static String create(int minimum,int maximum){
        return new UidSeed(minimum, maximum).generate();
    }
    
    public static String create(){
        return new UidSeed().generate();
    }
}
