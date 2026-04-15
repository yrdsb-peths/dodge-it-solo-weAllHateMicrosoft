import greenfoot.*;

public class MyWorld extends World {
    
    public MyWorld() {
        super(600, 400, 1,false);
        
        Dio dio = new Dio();
        addObject(dio,100,100);
        
        Roadroller roadroller = new Roadroller();
        addObject(roadroller,400,100);
        

        //Sample: this is how you call a banner
        //addObject(new Banner(BossConfig.DIO), 1120, 200);
    }
    
}
