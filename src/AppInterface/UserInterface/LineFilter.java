package AppInterface.UserInterface;

import javax.swing.*;

public class LineFilter extends JComboBox<String> {

    private final String[] lines = {
        "Выберите ветку",
                "Красная",
                "Зеленая",
                "Синяя",
                "Голубая",
                "Кольцевая",
                "Оранжевая",
                "Фиолетовая",
                "Желтая",
                "Серая",
                "Салатовая",
                "Бирюзовая",
                "Серо-голубая",
                "МЦК"};

    private int active;

    public LineFilter(){

        super();

        for(String line: lines){
            this.addItem(line);
        }

        active = -1;
    }

}
