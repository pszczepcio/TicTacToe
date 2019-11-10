package sample;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Sign {

    public static Text getSign(String sign){
        Text text = new Text(sign);
        text.setFont(Font.font(100));
        text.setFill(Color.BLUE);
        return text;
    }
}
