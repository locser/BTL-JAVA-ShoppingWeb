package com.BTL.globaldata;

import java.util.ArrayList;
import java.util.List;

import com.BTL.entity.Product;


public class GlobalData {
    //tao bien toan cuc
    public static int customerId;
    // 1 là của admin hay nếu mà khi chưa đăng nhập hay là muốn mua ngay thì tạo dữ liệu vào admin
    static {
        customerId=1;
    }
}
