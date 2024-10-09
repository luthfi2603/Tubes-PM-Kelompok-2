# Tubes-PM-Kelompok-2

## Info

Nama aplikasi: Bookjas  
Tim pengembang: Kelompok 2

- Ruth Grace Arlyana Manurung - 221402015  
  Role *()*
- Rifqi Jabrah Rhae - 221402031  
  Back-End *()*
- Muhammad Luthfi - 221402050  
  Back-End *()*
- Andy Septiawan Saragih - 221402053  
  Role *()*
- Ceycylia Dear Amizafatel - 221402059  
  Role *()*
- Serafim Edgar Pandamei SItorus - 221402133 (Project Manager)  
  Role *()*

## Desc

Bookjas (Book Journal System) adalah  aplikasi berbasis mobile yang dirancang untuk mempermudah pustakwan dalam pengelolaan inventaris buku di perpustakaan. Dengan basis mobile dapat mempermudah pustakawan dalam melacak, mengelola, dan mengoptimalkan buku-buku secara efisien. Selain itu, aplikasi ini juga berguna sebagai direktori perpustakaan untuk para pengunjung perpustakaan untuk mencari tahu informasi dari buku-buku yang ada di perpustakaan. 

## Features

### Guest

- Melihat daftar post.
    - Daftar semua post
    - Daftar post berdasarkan kategori
    - Daftar post berdasarkan pengguna
    - Daftar post berdasarkan kata kunci judul
- Melihat tulisan post
- Login & register
- Verifikasi email & lupa sandi

### User

#### Blog

- Mengirim komentar ke postingan
- Melaporkan postingan
- Melaporkan komentar
- Edit profile
- Ganti password dan email
- Logout

#### Dashboard

- Halaman dashboard
- Daftar post.
    - Mencari post sendiri
    - Membuat, menghapus, memperbarui post
    - Mengubah status post (published/draft)
- Daftar comment.
    - Melihat komentar dari post sendiri
- Fitur search di halaman posts & comments

### Admin (Dashboard)

- Halaman dashboard
- Kelola user
- Kelola postingan
- Kelola komentar
- Kelola kategori (CRUD)
- Kelola jenis report (CRUD)
- Kelola laporan post
- Kelola laporan komentar
- Fitur di masing-masing pengelolaan

Beberapa sumber daya luar yang dipakai dalam aplikasi ini:

- [Trix Editor](https://github.com/basecamp/trix) untuk membuat tulisan.
- [Eloquent-Sluggable](https://github.com/cviebrock/eloquent-sluggable) untuk membuat slug.
- [Mailtrap](https://mailtrap.io/) untuk mengirim email dalam mode pengembangan.
- [Sweetalert](https://sweetalert2.github.io/) untuk memberi notifikasi di bagian create dan edit post.

## Environment

Beberapa syarat environment untuk menjalankan aplikasi ini:

- **Operating System**: Windows 11 Professional  
  RAM : 8GB  
  Harddisk : SSD 512GB  
  Processor : Inter Core i5  
  VGA : Intel® Iris® Xᵉ Graphics  
- **Kotlin**: Versi terbaru (>= 1.6.0)
- **Android Studio**: Versi terbaru dengan Android SDK 31 atau lebih tinggi.
- **JDK**: Java Development Kit versi 11.
- **Gradle**: Versi yang sesuai dengan Android Studio terbaru.

### Instalasi dan Setup

1. **Install Android Studio**
    - Download dan install Android Studio dari [Android Studio Official Website](https://developer.android.com/studio).
    - Pastikan Android SDK 31 atau lebih tinggi terinstal melalui SDK Manager.
2. **Install JDK**
    - Download dan install JDK versi 11 dari [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) atau [OpenJDK](https://openjdk.java.net/install/).
3. **Clone Repository**
    - Clone repository ini ke lokal komputer:  
      ```bash
      git clone https://github.com/kelompok2/bookjas.git
      ```  
      ```
      cd bookjas
      ```