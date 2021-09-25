import service.CalculateCost;

public class AmexCodingTaskApplication {

    public static void main(String[] args){
        CalculateCost cc = new CalculateCost();
        cc.returnPrice(args);
    }
}
