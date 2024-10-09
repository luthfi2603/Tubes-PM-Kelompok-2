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

- PHP: Versi 8 (8.1.17)
- Database: MySQL
- Laravel: Versi 10 (10.48.9)
- Penyimpanan: Lokal

Terminal:

```
php artisan storage:link
```

.env (Laravel 10):

```
FILESYSTEM_DISK=public
```
