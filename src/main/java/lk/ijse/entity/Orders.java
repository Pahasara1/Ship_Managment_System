package lk.ijse.entity;

public class Orders {

        private String OrderId;
        private String OrderCompany;
        private String OrderCountry;
        private String StockId;
        private String AdminNic;
        private String Date;
        private String Time;

        public Orders() {

        }

        public Orders(String orderId, String orderCompany, String orderCountry, String stockId, String adminNic, String date, String time) {
            OrderId = orderId;
            OrderCompany = orderCompany;
            OrderCountry = orderCountry;
            StockId = stockId;
            AdminNic = adminNic;
            Date = date;
            Time = time;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String orderId) {
            OrderId = orderId;
        }

        public String getOrderCompany() {
            return OrderCompany;
        }

        public void setOrderCompany(String orderCompany) {
            OrderCompany = orderCompany;
        }

        public String getOrderCountry() {
            return OrderCountry;
        }

        public void setOrderCountry(String orderCountry) {
            OrderCountry = orderCountry;
        }

        public String getStockId() {
            return StockId;
        }

        public void setStockId(String itemId) {
            StockId = itemId;
        }

        public String getAdminNic() {
            return AdminNic;
        }

        public void setAdminNic(String adminNic) {
            AdminNic = adminNic;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        @Override
        public String toString() {
            return "OrdersTM{" +
                    "OrderId='" + OrderId + '\'' +
                    ", OrderCompany='" + OrderCompany + '\'' +
                    ", OrderCountry='" + OrderCountry + '\'' +
                    ", ItemId='" + StockId + '\'' +
                    ", AdminNic='" + AdminNic + '\'' +
                    ", Date='" + Date + '\'' +
                    ", Time='" + Time + '\'' +
                    '}';
        }
}
