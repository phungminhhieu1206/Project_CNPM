# Project_CNPM
Quản lý thông tin phòng tránh Covid 2019<br/>
- Nội dung:<br/>
+ Quản lý thông tin nhân khẩu<br/>
+ Khai báo thông tin dịch tễ: có đi từ vùng dịch không, tiếp xúc với người bệnh không, khai báo triệu chứng, khai báo bệnh, ...<br/>
+ Khai báo thông tin cách ly: ngày giờ cách ly, loại cách ly, khai báo các lần test covid, ...<br/>
+ Thống kê theo nhóm nghiệp vụ.<br/>

# Download và install các công cụ dưới đây:
 - [Netbean 8.2](https://mirror.downloadvn.com/apache/netbeans/netbeans/12.0/Apache-NetBeans-12.0-bin-windows-x64.exe)<br/>
 - [InteliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)<br/>
 - Phần mềm web server [XAMPP](https://www.apachefriends.org/download.html) mới nhất<br/>
 - Phần mềm quản lý mã nguồn mở [Github](https://git-scm.com/downloads) (cách cài đặt tại https://openplanning.net/10283/su-dung-github-voi-github-desktop#a70405)<br/>

# Add Libraries
(I) mysql-connector-java-5.1.49<br/>
    1 - download the mysql connector: https://dev.mysql.com/downloads/file/?id=496255<br/>
    choose: No thanks, just start my download.<br/>
    2 - extract the file<br/>
    3 - add file: mysql-connector-java-5.1.49.jar to your project<br/>
    4 - open xampp<br/>
    5 - start apache and mysql<br/>
    6 - go to phpmyadmin<br/>
    7 - create new database<br/>

(II) jcalendar-1.2.2<br/>
    https://tapchilaptrinh.vn/2012/10/11/cai-dat-them-jcalendar-cho-netbeans/<br/>
    then add file: jcalendar-1.2.2.jar to your project

# Khởi chạy
1. Khởi động phần mềm XAMPP (nếu bị lỗi port có thể xem tại [đây](https://hoangluyen.com/huong-dan-xu-ly-loi-port-xampp-nhanh-gon/))
Start Apache và MySQL<br/>
2. Import database
- Vào link [localhost:8080/phpMyAdmin/](http://localhost:8080/phpmyadmin/) (có thể thay 8080 thành port khác tuỳ theo máy)
- Chọn new(cột bên trái), điền tên project, sau đó Import (thanh bảng chọn ngang bên trên giao diện) file database<br/>
3. Chạy file: Project_CNPM\Covid19_Management_System\dist\Covid19_Management_System.jar. Hoặc chạy project trên netbean.
