package oop.bufihofa.app;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oop.bufihofa.classes.Item;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import oop.bufihofa.classes.*;

public class ui implements Initializable {
    @FXML
    CheckBox tenCheck, slCheck, dvCheck, giaCheck;
    @FXML
    Label nhapmakho1, nhapdiachikho1, infoStorage, danhsachhanghoa, errorLabel, idLabel, soLuongKQ;;
    @FXML
    TextField nhapmakho, nhapdiachikho, nameField, quantityField, unitField, priceField, searchField;
    @FXML
    Button suathongtinkho;
    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, Integer> idColumn, priceColumn;
    @FXML
    private TableColumn<Item, String> nameColumn, unitColumn, dateColumn;
    @FXML
    private TableColumn<Item, Double> quantityColumn;
    @FXML
    private DatePicker datePicker;

    boolean fileOpened = false;
    ThongBao thongBao = new ThongBao();
    FileChooser fileChooser = new FileChooser();
    File selectedFile;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    Storage kho = new Storage("", "");

    private ObservableList<Item> storage = FXCollections.observableArrayList();
    private ObservableList<Item> dataList = FXCollections.observableArrayList();
    private int ID = 1000000000;

    public void hideSuaThongTinKho(){
        suathongtinkho.setText("Sửa thông tin kho");
        nhapmakho.setVisible(false);
        nhapmakho1.setVisible(false);
        nhapdiachikho.setVisible(false);
        nhapdiachikho1.setVisible(false);
        danhsachhanghoa.setVisible(true);
    }
    public void unhideSuaThongTinKho(){
        suathongtinkho.setText("Xác Nhận");
        nhapmakho.setVisible(true);
        nhapmakho1.setVisible(true);
        nhapmakho.setText(kho.getMaKho());
        nhapdiachikho.setVisible(true);
        nhapdiachikho.setText(kho.getDiaChiKho());
        nhapdiachikho1.setVisible(true);
        danhsachhanghoa.setVisible(false);
    }
    public void suaThongTinKho(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        if(nhapmakho.isVisible()){
            hideSuaThongTinKho();
            kho.setMaKho(nhapmakho.getText());
            kho.setDiaChiKho(nhapdiachikho.getText());
            thongBao.thongTinKho(kho);
        }
        else{
            unhideSuaThongTinKho();
        }
    }
    public void xemThongTinKho(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        thongBao.thongTinKho(kho);
    }

    public void newStorageClick(){
        hideSuaThongTinKho();
        fileChooser.setTitle("Chọn nơi tạo mới File Dữ Liệu (*.csv)");
        fileChooser.setInitialFileName("NewStorage.csv");
        selectedFile = fileChooser.showSaveDialog(new Stage());
        if(selectedFile != null && selectedFile.getName().endsWith(".csv")){
            try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("id,name,quantity,unit,price,date\n.....\n.....");
                kho.setMaKho("");
                kho.setDiaChiKho("");
                bufferedWriter.close();
                fileOpened = true;
                infoStorage.setText(selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                infoStorage.setText("Tạo file thất bại");

                fileOpened = false;
            }
        }
        else{
            infoStorage.setText("Tạo file thất bại");
            fileOpened = false;
        }
        if(fileOpened == false){
            thongBao.taoKhoThatBai();
        }
        else{
            thongBao.taoKhoThanhCong();
        }
        storage.clear();
        table.setItems(storage);

    }
    public void importStorageClick(){
        hideSuaThongTinKho();
        fileChooser.setTitle("Chọn nơi chứa File Dữ Liệu có sẵn (.*csv)");
        selectedFile = fileChooser.showOpenDialog(new Stage());
        boolean readfileok = true;
        storage.clear();
        table.setItems(storage);
        if(selectedFile != null && selectedFile.getName().endsWith(".csv")){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile.getAbsolutePath()), "UTF8"));

                String line;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    ++i;
                    if(i==1){
                        if(!line.equalsIgnoreCase( "id,name,quantity,unit,price,date")){
                            readfileok = false;
                            infoStorage.setText("Đọc File thất bại");
                            break;
                        }
                    }
                    else if(i==2){
                        kho.setMaKho(line);
                    }
                    else if(i==3){
                        kho.setDiaChiKho(line);
                    }
                    else{
                        String[] fields = line.split(",");
                        storage.add(new Item(fields[1], Integer.parseInt(fields[0]), (int)Double.parseDouble(fields[4]), Double.parseDouble(fields[2]), fields[3], LocalDate.parse(fields[5], formatter)));
                        if(ID < Integer.parseInt(fields[0])) ID=Integer.parseInt(fields[0]);
                    }
                }
                if(readfileok){
                    fileOpened = true;
                    infoStorage.setText(selectedFile.getAbsolutePath());
                    ID++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                infoStorage.setText("Đọc File thất bại");
                fileOpened = false;
            }
        }
        else{
            infoStorage.setText("Đọc File thất bại");
            fileOpened = false;
        }
        if(fileOpened == false){
            thongBao.nhapDuLieuThatBai();
        }
        else{
            thongBao.nhapDuLieuThanhCong(kho);
        }
        table.setItems(storage);
    }
    public void saveStorageClick(){
        hideSuaThongTinKho();
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        if(selectedFile != null){
            try {
                FileWriter fileWriter = new FileWriter(selectedFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("id,name,quantity,unit,price,date\n");
                bufferedWriter.write(kho.getMaKho()+"\n");
                bufferedWriter.write(kho.getDiaChiKho()+"\n");
                for(int i = 0;i<storage.size();++i){
                    bufferedWriter.write(storage.get(i).getId()+","+storage.get(i).getName()+","+storage.get(i).getQuantity()+","+storage.get(i).getUnit()+","+storage.get(i).getPrice()+","+storage.get(i).getDateString()+"\n");
                }
                bufferedWriter.close();
                infoStorage.setText("Lưu thành công!");
            } catch (IOException e) {
                e.printStackTrace();
                infoStorage.setText("Lưu thất bại!");
            }
        }
        else{
            infoStorage.setText("Lưu thất bại!");
        }
        if(infoStorage.getText().equalsIgnoreCase("Lưu thất bại!")){
            thongBao.luuDuLieuThatBai();
        }
        else{
            thongBao.luuDuLieuThanhCong();
        }
    }
    public void onSearchResetButtonClicked(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        searchField.setText("");
        table.setItems(storage);
        tenCheckClick();
        //soLuongKQ.setText("Có "+storage.size()+" kết quả!");
    }
    public void search(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        dataList = FXCollections.observableArrayList();
        String searchText = searchField.getText();
        int m = searchText.length();
        if(m==0){
            table.setItems(storage);
            //soLuongKQ.setText("Có "+storage.size()+" kết quả!");
            return;
        }

        String subData = "";
        idLabel.setText(subData);
        nameField.setText(subData);
        unitField.setText(subData);
        priceField.setText(subData);
        quantityField.setText(subData);
        datePicker.setValue(null);
        int n = storage.size();
        for(int i = 0; i < n; ++i){
            if(tenCheck.isSelected())   subData = storage.get(i).getName();
            if(slCheck.isSelected())   subData = Double.toString(storage.get(i).getQuantity());
            if(dvCheck.isSelected())   subData = storage.get(i).getUnit();
            if(giaCheck.isSelected())   subData = Double.toString(storage.get(i).getPrice());
            if(subData.length()>=m){
                subData = subData.substring(0, m);
                if(searchText.equalsIgnoreCase(subData)){
                    dataList.add(storage.get(i));
                }
            }
        }

        table.setItems(dataList);
        soLuongKQ.setText("Có "+dataList.size()+" kết quả!");
    }

    public void onSearchButtonClicked(KeyEvent e){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }

        dataList = FXCollections.observableArrayList();


        String searchText = searchField.getText();
        if (e.getCode().getCode() >= 48 && e.getCode().getCode() <= 57) searchText = searchText + Integer.toString(e.getCode().getCode()-48);
        if(e.getCode().toString().length()==1) {
            char a = (e.getCode().toString().charAt(0));
            if (a >= 'A' && a <= 'Z') searchText = searchText + a;
            if (a >= 'a' && a <= 'z') searchText = searchText + a;
        }
        int m = searchText.length();
        if(m==0){
            table.setItems(storage);
            //soLuongKQ.setText("Có "+storage.size()+" kết quả!");
            return;
        }
        String subData = "";
        idLabel.setText(subData);
        nameField.setText(subData);
        unitField.setText(subData);
        priceField.setText(subData);
        quantityField.setText(subData);
        datePicker.setValue(null);
        int n = storage.size();
        for(int i = 0; i < n; ++i){
            if(tenCheck.isSelected())   subData = storage.get(i).getName();
            if(slCheck.isSelected())   subData = Double.toString(storage.get(i).getQuantity());
            if(dvCheck.isSelected())   subData = storage.get(i).getUnit();
            if(giaCheck.isSelected())   subData = Double.toString(storage.get(i).getPrice());
            if(subData.length()>=m){
                subData = subData.substring(0, m);
                if(searchText.equalsIgnoreCase(subData)){
                    dataList.add(storage.get(i));
                }
            }
        }

        table.setItems(dataList);
        soLuongKQ.setText("Có "+dataList.size()+" kết quả!");

    }
    @Override
    public void initialize(URL location, ResourceBundle rcs){
        hideSuaThongTinKho();
        storage = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("unit"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("dateString"));

        table.setItems(storage);
        table.setEditable(true);
        tenCheck.setSelected(true);
        importStorageClick();
    }
    public void printError(String s){
        errorLabel.setText(s);
    }
    //them
    public void onAddButtonClicked(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        printError("");
        String name = nameField.getText();
        String unit = unitField.getText();
        int price = 0;
        double quantity = 0;
        LocalDate date = LocalDate.of(2000, 1, 1);
        boolean kt = true;
        if(name.isEmpty()){
            printError("Tên không hợp lệ!");
            kt = false;
        }
        if(unit.isEmpty()){
            printError("Đơn vị không hợp lệ!");
            kt = false;
        }
        if(datePicker.getValue() == null){
            printError("Ngày không hợp lệ!");
            kt = false;
        }
        if(!priceField.getText().isEmpty()) {
            try {
                price = Integer.parseInt(priceField.getText());

            } catch (Exception e){
                printError("Giá không hợp lệ!");
                kt = false;
            }
        }
        if(!quantityField.getText().isEmpty()){
            try {
                quantity = Double.parseDouble(quantityField.getText());
            } catch (Exception e){
                printError("Số lượng không hợp lệ!");
                kt = false;
            }
        }
        if(datePicker.getValue() != null){
            try {
                date = datePicker.getValue();
            } catch (Exception e){
                printError("Ngày không hợp lệ!");
                kt = false;
            }
        }
        if(kt){
            storage.add(new Item(name, ID++, price, quantity, unit, date));
            search();
        }
    }
    //xem
    public void onTableClicked(MouseEvent e){
        if(!fileOpened) return;
        Item selected = table.getSelectionModel().getSelectedItem();
        if(selected != null) {
            idLabel.setText("ID:" + Integer.toString(selected.getId()));
            nameField.setText(selected.getName());
            unitField.setText(selected.getUnit());
            priceField.setText(Integer.toString(selected.getPrice()));
            quantityField.setText(Double.toString(selected.getQuantity()));
            datePicker.setValue(selected.getDate());
        }
    }
    //sua
    public void onEditButtonClicked(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        printError("");
        String name = nameField.getText();
        String unit = unitField.getText();
        int price = 0;
        double quantity = 0;

        boolean kt = true;
        if(name.isEmpty()){
            printError("Tên không hợp lệ!");
            kt = false;
        }
        if(unit.isEmpty()){
            printError("Đơn vị không hợp lệ!");
            kt = false;
        }
        if(datePicker.getValue() == null){
            printError("Ngày không hợp lệ!");
            kt = false;
        }
        if(!priceField.getText().isEmpty()) {
            try {
                price = Integer.parseInt(priceField.getText());

            } catch (Exception e){
                printError("Giá không hợp lệ!");
                kt = false;
            }
        }
        if(!quantityField.getText().isEmpty()){
            try {
                quantity = Double.parseDouble(quantityField.getText());
            } catch (Exception e){
                printError("Số lượng không hợp lệ!");
                kt = false;
            }
        }
        if(kt){
            Item selected = table.getSelectionModel().getSelectedItem();
            try {
                selected.setPrice(price);
                selected.setName(name);
                selected.setQuantity(quantity);
                selected.setUnit(unit);
                selected.setDate(datePicker.getValue());
                table.refresh();
                printError("Sửa thành công ID:" + selected.getId());
                search();
            }
            catch(Exception e){
                printError(e.getMessage());
            }
        }
    }
    public void onRemoveButtonClicked(){
        if(!fileOpened) {
            thongBao.chuaChonKho();
            return;
        }
        Item selected = table.getSelectionModel().getSelectedItem();
        try {
            storage.remove(selected);
            search();
        }
        catch(Exception e){
            printError(e.getMessage());
        }
    }

    public void anyCheckClick(){
        tenCheck.setSelected(false);
        slCheck.setSelected(false);
        dvCheck.setSelected(false);
        giaCheck.setSelected(false);
    }
    public void tenCheckClick(){
        anyCheckClick();
        tenCheck.setSelected(true);
        search();
    }
    public void slCheckClick(){
        anyCheckClick();
        slCheck.setSelected(true);
        search();
    }
    public void dvCheckClick(){
        anyCheckClick();
        dvCheck.setSelected(true);
        search();
    }
    public void giaCheckClick(){
        anyCheckClick();
        giaCheck.setSelected(true);

        search();
    }
}