import service.CalculateCost;

public class AmexCodingTaskApplication {

    public static void main(String[] args){
        CalculateCost cc = new CalculateCost();
        String[] temp = new String[]{"Orange","Orange","Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple"};

        cc.returnPrice(temp);
    }
}
