package model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pemesanan {
    public int idPemesanan;
    public int idPenerbangan;
    public String namaPelanggan;
    public int jumlah;
    public double totalHarga;
    public LocalDateTime waktuPesan;

    public Pemesanan(int idPemesanan, int idPenerbangan, String namaPelanggan, int jumlah, double totalHarga, LocalDateTime waktuPesan) {
        this.idPemesanan = idPemesanan;
        this.idPenerbangan = idPenerbangan;
        this.namaPelanggan = namaPelanggan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.waktuPesan = waktuPesan;
    }

    private String namaBulan(int bulan) {
        return switch (bulan) {
            case 1 -> "Januari";
            case 2 -> "Februari";
            case 3 -> "Maret";
            case 4 -> "April";
            case 5 -> "Mei";
            case 6 -> "Juni";
            case 7 -> "Juli";
            case 8 -> "Agustus";
            case 9 -> "September";
            case 10 -> "Oktober";
            case 11 -> "November";
            case 12 -> "Desember";
            default -> "Bulan tidak valid";
        };
    }

    public String ringkasan(Penerbangan penerbangan) {
        String infoPenerbangan;

        if (penerbangan != null) {
            String namaBulan = namaBulan(penerbangan.bulan);
            infoPenerbangan = penerbangan.pesawat + " " + penerbangan.asal + " -> " + penerbangan.tujuan + " (" + (penerbangan.hari < 10 ? "0" + penerbangan.hari : penerbangan.hari) + " " + namaBulan + " " + penerbangan.tahun + ")";
        } else {
            infoPenerbangan = "Penerbangan sudah dihapus";
        }

        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DecimalFormat df = new DecimalFormat("#,###");

        return "ID pesanan #" + idPemesanan +
                " | Nama: " + namaPelanggan +
                " | " + infoPenerbangan +
                " | Jumlah: " + jumlah +
                " | Total: Rp" + df.format(totalHarga) +
                " | Waktu pemesanan: " + waktuPesan.format(formatTanggal);
    }
}
