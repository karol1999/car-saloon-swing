package com.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Main
{
    private JTable table1;
    private JPanel panel1;
    private JList listOfCenters;
    private JButton deleteButton;
    private JButton addCenter;
    private JButton addVehicle;
    private JButton sortByLoad;
    private JButton getVehicle;

    public Main()
    {
        CarShowroomD carShowroomData = new CarShowroomD(); // dane odnośnie aut itd

        DefaultListModel listModel = new DefaultListModel();
        listOfCenters.setModel(listModel);

        String[] carArray = carShowroomData.carShowroomC.arrayOfShowrooms();
        listOfCenters.setListData(carArray);

        String[] columns = {"Marka","Model","Condition","Price","Year of production","Mileage","Engine capacity"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0)
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1.setModel(tableModel);

        listOfCenters.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                    tableModel.setRowCount(0);
                    String selected = (String) listOfCenters.getSelectedValue();
                    List<Vehicle> list = carShowroomData.carShowroomC.selectedShowroom(selected).getCarList();

                    Object rowData[] = new Object[7];
                    for (int i = 0; i < list.size(); i++)
                    {
                        rowData[0] = list.get(i).getBrand();
                        rowData[1] = list.get(i).getModel();
                        rowData[2] = list.get(i).getCondition();
                        rowData[3] = list.get(i).getPrice();
                        rowData[4] = list.get(i).getYearOfProduction();
                        rowData[5] = list.get(i).getMileage();
                        rowData[6] = list.get(i).getEngineCapacity();
                        tableModel.addRow(rowData);
                    }
            }
        });

        deleteButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String selected = (String) listOfCenters.getSelectedValue();
                int row=table1.getSelectedRow();
                System.out.println(row);

                // usuwanie najpierw pojedynczego modelu i wszystkich danych z nim związanych
                if (row!=-1)
                {
                    Vehicle vehicleRemove = new Vehicle((String) table1.getModel().getValueAt(row, 0), (String) table1.getModel().getValueAt(row, 1),
                            (ItemCondition) table1.getModel().getValueAt(row, 2), (double) table1.getModel().getValueAt(row, 3),
                            (int) table1.getModel().getValueAt(row, 4), (double) table1.getModel().getValueAt(row, 5),
                            (double) table1.getModel().getValueAt(row, 6), 1);
                    carShowroomData.carShowroomC.selectedShowroom(selected).removeProduct(vehicleRemove);

                    tableModel.setRowCount(0);
                    List<Vehicle> list = carShowroomData.carShowroomC.selectedShowroom(selected).getCarList();

                    Object rowData[] = new Object[7];
                    for (int i = 0; i < list.size(); i++)
                    {
                        rowData[0] = list.get(i).getBrand();
                        rowData[1] = list.get(i).getModel();
                        rowData[2] = list.get(i).getCondition();
                        rowData[3] = list.get(i).getPrice();
                        rowData[4] = list.get(i).getYearOfProduction();
                        rowData[5] = list.get(i).getMileage();
                        rowData[6] = list.get(i).getEngineCapacity();
                        tableModel.addRow(rowData);
                    }
                }

                // usuwanie całego salonu

                if (row==-1)
                {
                    carShowroomData.carShowroomC.removeCenter((String) listOfCenters.getSelectedValue());
                    String[] arrayOfCars = carShowroomData.carShowroomC.arrayOfShowrooms();
                    listOfCenters.setListData(arrayOfCars);
                    tableModel.setRowCount(0);
                }
            }
        });

        addCenter.addMouseListener(new MouseAdapter() // dodanie salonuq
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JTextField showroomName = new JTextField();
                JTextField showroomCapacity = new JTextField();
                Object[] message =
                        {
                        "Nazwa Salonu:", showroomName,
                        "Pojemność salonu:", showroomCapacity,
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Dodaj salon", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION)
                {
                    String name= String.valueOf(showroomName);
                    CarShowroom carShowroom = new CarShowroom(showroomName.getText(),Integer.parseInt(showroomCapacity.getText()));
                    carShowroomData.carShowroomC.addCenter(showroomName.getText(),carShowroom);
                    String[] arrayOfCars = carShowroomData.carShowroomC.arrayOfShowrooms();
                    listOfCenters.setListData(arrayOfCars);
                    tableModel.setRowCount(0);
                }
            }
        });

        addVehicle.addMouseListener(new MouseAdapter() // dodanie samochodu
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField mark = new JTextField();
                JTextField model = new JTextField();
                JTextField itemCondition = new JTextField();
                JTextField price = new JTextField();
                JTextField yearOfProduction = new JTextField();
                JTextField mileage = new JTextField();
                JTextField engineCapacity = new JTextField();
                Object[] message = {
                        "Marka:", mark,
                        "Model:", model,
                        "Stan:", itemCondition,
                        "Cena:", price,
                        "Rok produkcji:", yearOfProduction,
                        "Przebieg:", mileage,
                        "Pojemność silnika:", engineCapacity,
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Dodaj auto", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION){
                    Vehicle vehicle=new Vehicle(mark.getText(),model.getText(),ItemCondition.valueOf(itemCondition.getText().toUpperCase()),
                            Double.parseDouble(price.getText()),Integer.parseInt(yearOfProduction.getText()),Double.parseDouble(mileage.getText()),
                            Double.parseDouble(engineCapacity.getText()),1);
                    String selected = (String) listOfCenters.getSelectedValue();
                    carShowroomData.carShowroomC.selectedShowroom(selected).addProduct(vehicle);
                    tableModel.setRowCount(0);
                    List<Vehicle> list = carShowroomData.carShowroomC.selectedShowroom(selected).getCarList();
                    Object rowData[] = new Object[7];
                    for (int i = 0; i < list.size(); i++) {
                        rowData[0] = list.get(i).getBrand();
                        rowData[1] = list.get(i).getModel();
                        rowData[2] = list.get(i).getCondition();
                        rowData[3] = list.get(i).getPrice();
                        rowData[4] = list.get(i).getYearOfProduction();
                        rowData[5] = list.get(i).getMileage();
                        rowData[6] = list.get(i).getEngineCapacity();
                        tableModel.addRow(rowData);
                    }
                }
            }
        });

        sortByLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String [] arrayOfCars=carShowroomData.carShowroomC.arrayOfSortedShowrooms();
                listOfCenters.setListData(arrayOfCars);
                tableModel.setRowCount(0);
            }
        });

        getVehicle.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                String selected = (String) listOfCenters.getSelectedValue();
                int row=table1.getSelectedRow();
                System.out.println(row);

                if (row!=-1)
                {
                    Vehicle vehicleRemove = new Vehicle((String) table1.getModel().getValueAt(row, 0), (String) table1.getModel().getValueAt(row, 1),
                            (ItemCondition) table1.getModel().getValueAt(row, 2), (double) table1.getModel().getValueAt(row, 3),
                            (int) table1.getModel().getValueAt(row, 4), (double) table1.getModel().getValueAt(row, 5),
                            (double) table1.getModel().getValueAt(row, 6), 1);
                    carShowroomData.carShowroomC.selectedShowroom(selected).removeProduct(vehicleRemove);

                    tableModel.setRowCount(0);
                    List<Vehicle> list = carShowroomData.carShowroomC.selectedShowroom(selected).getCarList();

                    Object rowData[] = new Object[7];
                    for (int i = 0; i < list.size(); i++)
                    {
                        rowData[0] = list.get(i).getBrand();
                        rowData[1] = list.get(i).getModel();
                        rowData[2] = list.get(i).getCondition();
                        rowData[3] = list.get(i).getPrice();
                        rowData[4] = list.get(i).getYearOfProduction();
                        rowData[5] = list.get(i).getMileage();
                        rowData[6] = list.get(i).getEngineCapacity();
                        tableModel.addRow(rowData);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CarShowrooms");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(1000,500));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
