import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by dubsta on 28.03.2017.
 */
public class ObstaclePainter{

    // 0 ... not drawing
    // 1 ... adding obstacles
    // 2 ... removing obstacles
    public int draw_mode = 0;
    PGraphics pg;
    private PApplet m;

    float size_paint = 15;
    float size_clear = size_paint * 2.5f;

    float paint_x, paint_y;
    float clear_x, clear_y;

    int shading = 64;

    public ObstaclePainter(PGraphics pg, PApplet applet){
        this.pg = pg;
        this.m = applet;
    }

    public void beginDraw(int mode){
        paint_x = m.mouseX;
        paint_y = m.mouseY;
        this.draw_mode = mode;
        if(mode == 1){
            pg.beginDraw();
            pg.blendMode(m.REPLACE);
            pg.noStroke();
            pg.fill(shading);
            pg.ellipse(m.mouseX, m.mouseY, size_paint, size_paint);
            pg.endDraw();
        }
        if(mode == 2){
            clear(m.mouseX,m.mouseY);
        }
    }

    public boolean isDrawing(){
        return draw_mode != 0;
    }

    public void draw(){
        paint_x = m.mouseX;
        paint_y = m.mouseY;
        if(draw_mode == 1){
            pg.beginDraw();
            pg.blendMode(m.REPLACE);
            pg.strokeWeight(size_paint);
            pg.stroke(shading);
            pg.line(m.mouseX, m.mouseY, m.pmouseX, m.pmouseY);
            pg.endDraw();
        }
        if(draw_mode == 2){
            clear(m.mouseX, m.mouseY);
        }
    }

    public void endDraw(){
        this.draw_mode = 0;
    }

    public void clear(float x, float y){
        clear_x = x;
        clear_y = y;
        pg.beginDraw();
        pg.blendMode(m.REPLACE);
        pg.noStroke();
        pg.fill(0, 0);
        pg.ellipse(x, y, size_clear, size_clear);
        pg.endDraw();
    }

    public void displayBrush(PGraphics dst){
        if(draw_mode == 1){
            dst.strokeWeight(1);
            dst.stroke(0);
            dst.fill(200,50);
            dst.ellipse(paint_x, paint_y, size_paint, size_paint);
        }
        if(draw_mode == 2){
            dst.strokeWeight(1);
            dst.stroke(200);
            dst.fill(200,100);
            dst.ellipse(clear_x, clear_y, size_clear, size_clear);
        }
    }


}