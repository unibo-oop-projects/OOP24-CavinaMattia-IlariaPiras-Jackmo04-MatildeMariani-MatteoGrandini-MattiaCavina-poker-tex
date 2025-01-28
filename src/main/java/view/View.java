package view;

import view.commons.Scene;

public interface View {
    
    void changeScene(Scene scene);

    int getScreenWidth();

    int getScreenHeight();
}
