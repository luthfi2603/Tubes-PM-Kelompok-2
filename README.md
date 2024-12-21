# Tubes-PM-Kelompok-2

## Info

Nama aplikasi: Bookjas  
Tim pengembang: Kelompok 2

- Ruth Grace Arlyana Manurung - 221402015  
  Front-End 
- Rifqi Jabrah Rhae - 221402031  
  Back-End 
- Muhammad Luthfi - 221402050  
  Back-End & Front-End 
- Andy Septiawan Saragih - 221402053  
  UI/UX Design 
- Ceycylia Dear Amizafatel - 221402059  
  Documentation, Front-End 
- Serafim Edgar Pandamei SItorus - 221402133 (Project Manager)  
  Back-End 

## Desc

Bookjas (Book Journal System) adalah  aplikasi berbasis mobile yang dirancang untuk mempermudah pustakwan dalam pengelolaan inventaris buku di perpustakaan. Dengan basis mobile dapat mempermudah pustakawan dalam melacak, mengelola, dan mengoptimalkan buku-buku secara efisien. Selain itu, aplikasi ini juga berguna sebagai direktori perpustakaan untuk para pengunjung perpustakaan untuk mencari tahu informasi dari buku-buku yang ada di perpustakaan. 

## UI Design

[Link Figma](https://www.figma.com/design/xNDVeDLdNdsU6jKidOBbfW/BookJas---Mobile?node-id=0-1&t=p7ytFbfWdCg9IHS5-1)

## Features

### Guest

- Melakukan pendaftaran akun
- Verifikasi email
- Melihat buku-buku yang tersedia
    - Daftar semua buku
    - Daftar buku berdasarkan judul
    - Daftar buku berdasarkan kategori

### User (Anggota)

#### Autentikasi

- Melakukan login
- Melakukan lupa kata sandi

#### Dashboard

- Melihat daftar buku yang dipinjam
- Melihat daftar riwayat peminjaman
- Melihat rekomendasi buku

#### Buku

- Melihat buku-buku yang tersedia
    - Daftar semua buku
    - Daftar buku berdasarkan judul
    - Daftar buku berdasarkan kategori

#### Profile

- Melihat profile
- Mengubah profile
- Melihat kartu anggota
- Mengganti password
- Log out

### Admin (Pustakawan)

#### Autentikasi

- Melakukan login
- Melakukan lupa kata sandi

#### Dashboard

- Melihat statistik dari buku dan peminjaman
- Melihat daftar buku yang baru dipinjam
- Melihat daftar buku dengan tenggat terdekat
- Melihat daftar peminjaman
    - Menambahkan peminjaman
    - Memperbarui peminjaman
    - Menghapus peminjaman 

#### Buku

- Melihat buku-buku yang tersedia
    - Daftar semua buku
    - Daftar buku berdasarkan judul
    - Daftar buku berdasarkan kategori
- Menambahkan buku
- Memperbarui buku
- Menghapus buku

#### Kategori

- Melihat kategori
- Menambahkan kategori
- Memperbarui kategori
- Menghapus kategori

#### Profile

- Melihat profile
- Mengubah profile 
- Mengganti password
- Log out

## Library

- SplashScreen
  Kegunaan :
  Library atau fitur ini digunakan untuk menampilkan layar pembuka (splash screen) saat aplikasi pertama kali dijalankan. Biasanya digunakan untuk memperkenalkan logo, nama aplikasi, atau melakukan inisialisasi data sebelum aplikasi siap digunakan.

-	Retrofit
  Kegunaan :
  Retrofit adalah library dari Square yang digunakan untuk melakukan komunikasi HTTP/REST API secara efisien. Retrofit dapat mengirim dan menerima data dari API menggunakan objek Java atau Kotlin, membuat kode lebih bersih dan terstruktur.

-	Retrofit-Gson
  Kegunaan : 
  Modul tambahan untuk Retrofit yang memungkinkan parsing data JSON menjadi objek Kotlin/Java secara otomatis menggunakan Gson. Ini mempermudah pengelolaan data yang diterima atau dikirim melalui API.

-	Glide
  Kegunaan :
  Glide adalah library untuk pemrosesan gambar yang digunakan untuk memuat, menampilkan, dan meng-cache gambar dari internet atau penyimpanan lokal. Glide terkenal karena efisiensinya dalam mengelola memori dan performa aplikasi.

-	Glide-Compiler
  Kegunaan : 
  Modul pendukung untuk Glide yang digunakan untuk menghasilkan kode tambahan selama proses build, seperti anotasi untuk optimasi. Ini berguna untuk meningkatkan efisiensi dalam menggunakan Glide, terutama jika menggunakan fitur khusus seperti integrasi dengan library lain.

-	Kotlin Parcelize
  Kegunaan : 
  Plugin Kotlin yang mempermudah implementasi antarmuka Parcelable untuk objek data. Dengan anotasi @Parcelize, kita dapat menghindari boilerplate code saat mengirimkan objek data antar aktivitas atau fragmen.

-	CircleImageView
  Kegunaan : 
  Library khusus untuk membuat tampilan gambar berbentuk lingkaran. Sangat populer untuk menampilkan gambar profil atau avatar dalam aplikasi, karena mempermudah pengelolaan UI tanpa perlu kode tambahan untuk memotong atau memformat gambar.


## Permission

- INTERNET
  Deskripsi : Memberikan izin kepada aplikasi untuk mengakses jaringan internet. Izin ini penting jika aplikasi ingin mengakses sumber daya online, seperti API, server, atau website.
- ACCESS_NETWORK_STATE
  Deskripsi : Memberikan izin kepada aplikasi untuk memeriksa status jaringan (misalnya, apakah perangkat terhubung ke Wi-Fi atau data seluler).
- READ_EXTERNAL_STORAGE
  Deskripsi : Untuk membaca file dari penyimpanan eksternal.
- WRITE_EXTERNAL_STORAGE
  Deskripsi : Untuk menulis file ke penyimpanan eksternal.
- CLEARTEXT TRAFFIC
  Deskripsi : Mengizinkan aplikasi untuk menggunakan protokol HTTP (bukan HTTPS) dalam berkomunikasi dengan server.

## Environment

Beberapa syarat environment untuk menjalankan aplikasi ini:

- **Operating System**: Windows 11 Professional  
  RAM : 8GB  
  Harddisk : SSD 512GB  
  Processor : Inter Core i5  
  VGA : Intel® Iris® Xᵉ Graphics  
- **Kotlin**: Versi terbaru (>= 1.6.0)
- **Android Studio**: Versi terbaru dengan Android SDK 31 atau lebih tinggi.
- **JDK**: Java Development Kit versi 21.
- **Gradle**: Versi yang sesuai dengan Android Studio terbaru.

### Instalasi dan Setup
1. **Setup API**
    - Untuk informasi lebih lanjut bisa dilihat di [BookjasAPI](https://github.com/SerafimSitorus/bookjas-backend-api)
2. **Install Android Studio**
    - Download dan install Android Studio dari [Android Studio Official Website](https://developer.android.com/studio).
    - Pastikan Android SDK 31 atau lebih tinggi terinstal melalui SDK Manager.
3. **Install JDK**
    - Download dan install JDK versi 21 dari [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
4. **Clone Repository**
    - Clone repository ini ke lokal komputer:
      ```
      git clone https://github.com/luthfi2603/Tubes-PM-Kelompok-2.git
      ```
      ```
      cd Tubes-PM-Kelompok-2
      ```