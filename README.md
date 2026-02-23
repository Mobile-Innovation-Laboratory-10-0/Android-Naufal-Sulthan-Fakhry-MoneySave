# MoneySave

## Deskripsi
MoneySave adalah aplikasi manajemen keuangan pribadi berbasis Android yang dirancang untuk membantu pengguna mencatat pemasukan dan pengeluaran secara digital. Proyek ini dibuat sebagai evaluasi akhir untuk Tugas Besar MP Motion Laboratory 10.0.

## Fitur Utama
- Login dan Register menggunakan Firebase Authentication.
- Manajemen Transaksi (CRUD): Pengguna dapat menambah, melihat, mengubah, dan menghapus data transaksi.
- Perhitungan Saldo: Total saldo dihitung secara real-time berdasarkan riwayat transaksi.
- Penyimpanan Lokal: Menggunakan Room Database untuk menyimpan data transaksi secara offline.

## Screenshot
<img width="376" height="783" alt="image" src="https://github.com/user-attachments/assets/205bd6e2-0eb5-4bb5-8498-3546b1f8c4ab" />
<img width="353" height="786" alt="image" src="https://github.com/user-attachments/assets/6c220435-d2e2-4ff9-b692-a5b36ab962a3" />
<img width="364" height="796" alt="image" src="https://github.com/user-attachments/assets/d2bb31de-87f0-49ee-a6a5-f702235369e8" />


## Alur Navigasi dan Teknis
- Aplikasi dimulai dari autentikasi menggunakan Firebase Auth (Login/Register). Setelah berhasil, pengguna diarahkan ke Dashboard Utama (Home) yang menampilkan rekap saldo secara otomatis dari database.
- Data mengalir dari Room Database melalui Repository ke ViewModel. Di dalam ViewModel, saya menggunakan StateFlow untuk memastikan UI selalu mendapatkan data terbaru secara reaktif tanpa perlu memuat ulang halaman. - Sistem Navigasi & Passing Data: Perpindahan antar layar dikelola oleh Navigation Component. Khusus fitur Update, data transaksi (seperti ID dan nominal) dikirimkan melalui argumen rute navigasi dari halaman Home ke halaman Edit untuk mengisi form secara otomatis.
- Seluruh komponen aplikasi (Database, Repository, dan ViewModel) dihubungkan menggunakan Koin. Hal ini memastikan manajemen memori yang lebih efisien dan mempermudah pemeliharaan kode

## Cara Menjalankan
1. Clone repository ini.
2. Tambahkan file google-services.json dari Firebase ke folder app/.
3. Lakukan Gradle Sync di Android Studio.
4. Jalankan aplikasi pada Emulator atau Device fisik.

## Video Presentasi
Link video presentasi teknis:
https://youtu.be/U3UDbpcmJpE
---
Disusun oleh: Naufal Sulthan Fakhry - 607062400092
