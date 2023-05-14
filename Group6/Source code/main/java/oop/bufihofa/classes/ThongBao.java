package oop.bufihofa.classes;

import javafx.scene.control.Alert;
public class ThongBao {
    public void infor(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void alert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void chuaChonKho(){
        alert("Chưa chọn kho", "Tạo kho mới hoặc nhập dữ liệu kho để thực hiện thao tác này!");
    }
    public void thongTinKho(Storage kho){
        infor("Thông Tin Kho", "Mã Kho: "+kho.getMaKho()+"\nĐịa Chỉ Kho: "+kho.getDiaChiKho());
    }
    public void taoKhoThatBai(){
        alert("TẠO KHO THẤT BẠI", "Vui lòng đặt tên và tạo file đúng định dạng *.csv");
    }
    public void taoKhoThanhCong(){
        infor("TẠO KHO THÀNH CÔNG", "Bạn có thể điền thông tin cho kho");
    }
    public void nhapDuLieuThatBai(){
        alert("NHẬP DỮ LIỆU KHO THẤT BẠI", "Vui lòng chọn đúng file dữ liệu của kho");
    }
    public void nhapDuLieuThanhCong(Storage kho){
        infor("NHẬP DỮ LIỆU KHO THÀNH CÔNG", "Nhập dữ liệu kho thành công!\n\nMã Kho: "+kho.getMaKho()+"\nĐịa Chỉ Kho: "+kho.getDiaChiKho());
    }
    public void luuDuLieuThatBai(){
        alert("LƯU DỮ LIỆU KHO THẤT BẠI", "LƯU DỮ LIỆU KHO THẤT BẠI");
    }
    public void luuDuLieuThanhCong(){
        infor("LƯU DỮ LIỆU KHO THÀNH CÔNG", "LƯU DỮ LIỆU KHO THÀNH CÔNG");
    }
}
