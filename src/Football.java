public class Football extends Baall{
    private int velocityy = 0;
    public Football(int b, String o){
        super(b,o);
    }
    public Football(){

    }

    public void faster(int y){
        velocityy+=y;
    }

    public void drop(int x){
        super.inflate(x);

    }
}
