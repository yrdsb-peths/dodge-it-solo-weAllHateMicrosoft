import greenfoot.*;

public class MyWorld extends World {
    
    public MyWorld() {
        super(600, 400, 1,false);
        
        Hero hero = new Hero();
        addObject(hero,100,100);
        
        Roadroller roadroller = new Roadroller();
        addObject(roadroller,400,100);
        
        GreenfootImage dioImg = new GreenfootImage("dio.png");
        dioImg.scale(90,150);
        GreenfootImage dioLabel = new GreenfootImage("dio_label.png");
        dioLabel.scale(300,100);
        Banner.SpriteOverlay[] mySprites = new Banner.SpriteOverlay[2];
        mySprites[0] = new Banner.SpriteOverlay(dioImg, -200, -30);
        mySprites[1] = new Banner.SpriteOverlay(dioLabel, 0, 100);
        
        addObject(new Banner(mySprites), 1120, 200);
    }
    
}
