package AppInterface.UserInterface;

import javax.swing.*;

public class SortingFilter extends JComboBox<String> {

    private String[] sorts = {
            "Сортировка А-Я",
            "Сортировка Я-А",
            "От ... До ...",
            "От ... До ..."
    };

    public SortingFilter(){

        super();

        this.addItem(sorts[0]);
        this.addItem(sorts[1]);
    }

    public void changeLine(int lineID){
        
    }

}
