public class Baall {
    private int bounce;
    private String owner;
    private int velocityx;

    public Baall(int b, String o){
        bounce = b;
        owner=o;
    }

    public Baall() {
    }


    public String toString(){
        return owner;
    }
    public boolean equals(Baall b){
        if (b.toString().equals(this.toString())) return true;
        return false;
    }

    public void faster(){
        velocityx+=10;
    }

    public void inflate(int x){
        bounce +=x;
    }
}
