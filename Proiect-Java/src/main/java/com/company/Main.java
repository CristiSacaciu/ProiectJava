package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static JList<Car> jList;
    private static final DefaultListModel<Car> model = new DefaultListModel<>();
    private static JLabel makeLabel;
    private static JLabel modelLabel;
    private static JLabel yearLabel;
    private static JLabel priceLabel;
    private static JTextField makeTextField;
    private static JTextField modelTextField;
    private static JTextField yearTextField;
    private static JTextField priceTextField;
    private static JButton add;
    private static JButton modify;
    private static JButton delete;
    private static JButton update;


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000, 500));
        JToolBar toolBar = new JToolBar();
        JPanel jPanel = new JPanel();
        frame.add(jPanel);
        placeToolBarComponents(toolBar);
        placeAddNewCarComponents(jPanel);
        showAllComponents(jPanel);


        frame.getContentPane().add(toolBar,BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setTitle("Proiect java");
        frame.setVisible(true);
        frame.pack();
    }

    private static void showAllComponents(JPanel jPanel) {
        jList = new JList(model);
        List<Car> cars = FileUtility.readFromFile();
        for(Car car: cars)
           model.addElement(car);
        jPanel.add(jList);
        jList.setVisible(false);
        jList.setCellRenderer(createListRenderer());
        modify = new JButton("Modify");
        modify.setVisible(false);
        jPanel.add(modify);
        modify.addActionListener(actionEvent -> {
            setViewComponentsVisibility(false);
            setAddComponentsVisibility(true);
            add.setVisible(false);
            Car car = model.get(jList.getSelectedIndex());
            makeTextField.setText(car.getMake());
            modelTextField.setText(car.getCarModel());
            yearTextField.setText(car.getYear() + "");
            priceTextField.setText(car.getPrice()+ "");
            update.setVisible(true);
            add.setVisible(false);
        });
        delete = new JButton("Delete");
        delete.setVisible(false);
        jPanel.add(delete);
        delete.addActionListener(actionEvent -> {
            if(jList.getSelectedIndex() != -1)
                model.remove(jList.getSelectedIndex());
        });
        update.setVisible(false);
    }

    private static void placeAddNewCarComponents(JPanel jPanel) {
        makeLabel = new JLabel("Make");
        jPanel.add(makeLabel);
        makeTextField = new JTextField(15);
        jPanel.add(makeTextField);

        modelLabel = new JLabel("Model");
        jPanel.add(modelLabel);
        modelTextField = new JTextField(15);
        jPanel.add(modelTextField);

        yearLabel = new JLabel("Year");
        jPanel.add(yearLabel);
        yearTextField = new JTextField(15);
        jPanel.add(yearTextField);

        priceLabel = new JLabel("Price");
        jPanel.add(priceLabel);
        priceTextField = new JTextField(15);
        jPanel.add(priceTextField);

        add = new JButton("Add");
        jPanel.add(add);
        add.addActionListener(actionEvent -> {
            String make = makeTextField.getText();
            String carModel = modelTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            Car car = new Car(make, carModel, year, price);
            model.addElement(car);
            makeTextField.setText("");
            modelTextField.setText("");
            yearTextField.setText("");
            priceTextField.setText("");
            JOptionPane.showMessageDialog(null,"A new car was added!");
        });
        update = new JButton("Update");
        update.setVisible(false);
        jPanel.add(update);
        update.addActionListener(e -> {
            String make = makeTextField.getText();
            String carModel = modelTextField.getText();
            int year = Integer.parseInt(yearTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            Car car = new Car(make, carModel, year, price);
            model.set(jList.getSelectedIndex(), car);
            makeTextField.setText("");
            modelTextField.setText("");
            yearTextField.setText("");
            priceTextField.setText("");
            JOptionPane.showMessageDialog(null, "The car was updated!");
        });
    }

    private static void placeToolBarComponents(JToolBar toolBar) {
        JButton addNewObject = new JButton("Adaugati o noua masina");
        addNewObject.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setViewComponentsVisibility(false);
                setAddComponentsVisibility(true);
            }
        });
        toolBar.add(addNewObject);

        JButton showAllObjects = new JButton("Afisati toate masinile");
        toolBar.add(showAllObjects);
        showAllObjects.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setViewComponentsVisibility(true);
                setAddComponentsVisibility(false);
                update.setVisible(false);
            }
        });

        JButton exit = new JButton("Exit");
        toolBar.add(exit);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Car> cars = new ArrayList<>();
                for(int i = 0; i<model.size(); i++)
                    cars.add((Car) model.get(i));
                FileUtility.writeToFile(cars);
                System.exit(0);

            }
        });

    }

    private static void setViewComponentsVisibility(boolean b) {
        jList.setVisible(b);
        modify.setVisible(b);
        delete.setVisible(b);
    }

    private static void setAddComponentsVisibility(boolean b) {
        makeLabel.setVisible(b);
        modelLabel.setVisible(b);
        yearLabel.setVisible(b);
        priceLabel.setVisible(b);
        makeTextField.setVisible(b);
        modelTextField.setVisible(b);
        yearTextField.setVisible(b);
        priceTextField.setVisible(b);
        add.setVisible(b);

    }

    private static ListCellRenderer<? super Car> createListRenderer(){
        return new DefaultListCellRenderer(){
            private final Color background = new Color(0,100,255,15);
            private final Color defaultBackground = (Color) UIManager.get("List.background");

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean callHasFocus){
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, callHasFocus);
                if(c instanceof JLabel){
                    JLabel label = (JLabel) c;
                    Car car = (Car) value;
                    label.setText(car.getMake() + " ~ " + car.getCarModel() + " ~ anul: " + car.getYear() + " ~ pret: " + car.getPrice() + "$");
                    if(!isSelected){
                        label.setBackground(index % 2 == 0 ? background: defaultBackground);
                    }
                }
                return c;
            }
        };
    }
}
