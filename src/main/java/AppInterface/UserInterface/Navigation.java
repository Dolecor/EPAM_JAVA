package AppInterface.UserInterface;

import AppInterface.Panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Navigation extends JPanel {

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

    private final String[] sortMod = {
            "От А до Я",
            "От Я до А"};

    JComboBox<String> filterLineA;
    JComboBox<String> filterLineB;

//    JComboBox<String> filterSearchA;
//    JComboBox<String> filterSearchB;

    ArrayList<JRadioButton> sortA;
    ArrayList<JRadioButton> sortB;

    JPanel sortPanelA;
    JPanel sortPanelB;

    JComboBox<String> filterStationA;
    JComboBox<String> filterStationB;

    JLabel textA;
    JLabel textB;

    // TODO: Сделать при готовности основной части проекта
//    JTextField StationNameFieldA;
//    JTextField StationNameFieldB;

    public Navigation(){


        this.setLayout(new GridLayout(11,1));
        this.setBounds(0,500,500,500);

        filterLineA = new JComboBox<>(lines);
        filterLineB = new JComboBox<>(lines);

        sortA = new ArrayList<>();
        sortA.add(new JRadioButton(sortMod[0], true));
        sortA.add(new JRadioButton(sortMod[1], false));

        sortB = new ArrayList<>();
        sortB.add(new JRadioButton(sortMod[0], true));
        sortB.add(new JRadioButton(sortMod[1], false));

        filterStationA = new JComboBox<>();
        filterStationB = new JComboBox<>();

        textA = new JLabel("Выберите точку отправления");
        textB = new JLabel("Выберите точку прибытия");

        addAllActionListeners();

        addAllComponents();

    }

    private void addAllActionListeners(){
        addSortButtonsActionListeners();
        addFilterLinesActionListeners();
    }

    private void addAllComponents(){

        textA.setFont(new Font(null, Font.PLAIN, 14));
        textB.setFont(new Font(null, Font.PLAIN, 14));



        this.add(textA);
        this.add(filterLineA);
        this.add(sortA.get(0));
        this.add(sortA.get(1));
        this.add(filterStationA);

        this.add(new JPanel());

        this.add(textB);
        this.add(filterLineB);
        this.add(sortB.get(0));
        this.add(sortB.get(1));
        this.add(filterStationB);
    }

    private void addSortButtonsActionListeners(){

        sortA.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sortA.get(0).setSelected(true);
                sortA.get(1).setSelected(false);

                ArrayList<String> stations = new ArrayList<>();

                for (int i = 0; i < filterStationA.getItemCount(); i++)
                    stations.add(filterStationA.getItemAt(i));

                Collections.sort(stations);

                filterStationA.removeAllItems();

                for (String name: stations)
                    filterStationA.addItem(name);
            }
        });

        sortA.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortA.get(0).setSelected(false);
                sortA.get(1).setSelected(true);

                ArrayList<String> stations = new ArrayList<>();

                for (int i = 0; i < filterStationA.getItemCount(); i++)
                    stations.add(filterStationA.getItemAt(i));

                Collections.reverse(stations);

                filterStationA.removeAllItems();

                for (String name: stations)
                    filterStationA.addItem(name);
            }
        });

        sortB.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortB.get(0).setSelected(true);
                sortB.get(1).setSelected(false);

                ArrayList<String> stations = new ArrayList<>();

                for (int i = 0; i < filterStationB.getItemCount(); i++)
                    stations.add(filterStationB.getItemAt(i));

                Collections.sort(stations);

                filterStationB.removeAllItems();

                for (String name: stations)
                    filterStationB.addItem(name);
            }
        });

        sortB.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortB.get(0).setSelected(false);
                sortB.get(1).setSelected(true);


                ArrayList<String> stations = new ArrayList<>();

                for (int i = 0; i < filterStationB.getItemCount(); i++)
                    stations.add(filterStationB.getItemAt(i));

                Collections.reverse(stations);

                filterStationB.removeAllItems();

                for (String name: stations)
                    filterStationB.addItem(name);
            }
        });
    }


    private void addFilterLinesActionListeners(){

        filterLineA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> stations = new ArrayList<>();

                filterStationA.removeAllItems();
                if (filterLineA.getSelectedIndex() != 0){

                    stations.addAll(Panel.getStationsByWays().get(filterLineA.getSelectedIndex() - 1));

                    if (sortA.get(0).isSelected())
                        Collections.sort(stations);
                    else
                        Collections.reverse(stations);
                    for (String name: stations)
                        filterStationA.addItem(name);
                }
            }

        });

        filterLineB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> stations = new ArrayList<>();

                filterStationB.removeAllItems();
                if (filterLineB.getSelectedIndex() != 0){

                    stations.addAll(Panel.getStationsByWays().get(filterLineB.getSelectedIndex() - 1));

                    if (sortB.get(0).isSelected())
                        Collections.sort(stations);
                    else
                        Collections.reverse(stations);
                    for (String name: stations)
                        filterStationB.addItem(name);
                }
            }

        });

    }
}


