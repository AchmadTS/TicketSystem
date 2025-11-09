package controller;

public class TambahPenerbangan {
    private SistemTiket sistem;

    public TambahPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.print("Nama pesawat: ");
        String pesawat = sistem.input.nextLine();
        System.out.print("Asal: ");
        String asal = sistem.input.nextLine();
        System.out.print("Tujuan: ");
        String tujuan = sistem.input.nextLine();
        System.out.print("Harga: ");
        double harga = Double.parseDouble(sistem.input.nextLine());
        System.out.print("Tanggal (hari): ");
        int hari = Integer.parseInt(sistem.input.nextLine());
        System.out.print("Bulan (angka): ");
        int bulan = Integer.parseInt(sistem.input.nextLine());
        System.out.print("Tahun: ");
        int tahun = Integer.parseInt(sistem.input.nextLine());
        System.out.print("Jumlah kursi: ");
        int kursi = Integer.parseInt(sistem.input.nextLine());

        sistem.daftarPenerbangan[sistem.jumlahPenerbangan++] =
                new model.Penerbangan(sistem.nextIdPenerbangan++, pesawat, asal, tujuan, harga, bulan, hari, tahun, kursi);

        System.out.println("✅ Penerbangan ditambahkan.");
    }
}
