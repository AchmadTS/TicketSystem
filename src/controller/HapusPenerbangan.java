package controller;

public class HapusPenerbangan {
    private SistemTiket sistem;

    public HapusPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 🗑️ HAPUS PENERBANGAN                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);

        if (sistem.jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada penerbangan yang bisa dihapus.");
            return;
        }

        System.out.println();

        int idx = -1;
        while (idx == -1) {
            int id = 0;
            boolean idValid = false;
            while (!idValid) {
                System.out.print("Masukkan ID penerbangan yang akan dihapus: ");
                String inputId = sistem.input.nextLine().trim();

                if (inputId.isEmpty()) {
                    System.out.println("❌ ID tidak boleh kosong!");
                    continue;
                }

                boolean formatValid = true;
                for (int i = 0; i < inputId.length(); i++) {
                    char c = inputId.charAt(i);
                    if (c < '0' || c > '9') {
                        formatValid = false;
                        break;
                    }
                }

                if (!formatValid) {
                    System.out.println("❌ ID harus berupa angka!");
                    continue;
                }

                id = Integer.parseInt(inputId);
                idValid = true;
            }

            for (int i = 0; i < sistem.jumlahPenerbangan; i++) {
                if (sistem.daftarPenerbangan[i].id == id) {
                    idx = i;
                    break;
                }
            }

            if (idx == -1) {
                System.out.println("❌ Penerbangan dengan ID tersebut tidak ditemukan. Silakan coba lagi.");
            }
        }

        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│          🗑️ DATA PENERBANGAN YANG AKAN DIHAPUS           │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        System.out.println(sistem.daftarPenerbangan[idx]);
        System.out.println();

        int jumlahPemesananTerkait = 0;
        for (int i = 0; i < sistem.jumlahPemesanan; i++) {
            if (sistem.daftarPemesanan[i].idPenerbangan == sistem.daftarPenerbangan[idx].id) {
                jumlahPemesananTerkait++;
            }
        }

        if (jumlahPemesananTerkait > 0) {
            System.out.println("⚠️ PERINGATAN: Ada " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini!");
            System.out.println("Data pemesanan tiket akan ikut terhapus!");
            System.out.println();
        }

        boolean konfirmasiValid = false;
        while (!konfirmasiValid) {
            System.out.print("⚠️  Yakin ingin menghapus penerbangan ini? (y/n): ");
            String konfirmasi = sistem.input.nextLine().trim().toLowerCase();

            if (konfirmasi.equals("y")) {
                konfirmasiValid = true;
                int idPenerbanganYangDihapus = sistem.daftarPenerbangan[idx].id;
                for (int i = sistem.jumlahPemesanan - 1; i >= 0; i--) {
                    if (sistem.daftarPemesanan[i].idPenerbangan == idPenerbanganYangDihapus) {
                        // Geser array pemesanan ke kiri
                        for (int j = i; j < sistem.jumlahPemesanan - 1; j++) {
                            sistem.daftarPemesanan[j] = sistem.daftarPemesanan[j + 1];
                        }
                        sistem.jumlahPemesanan--;
                    }
                }

                for (int i = idx; i < sistem.jumlahPenerbangan - 1; i++) {
                    sistem.daftarPenerbangan[i] = sistem.daftarPenerbangan[i + 1];
                }
                sistem.jumlahPenerbangan--;

                if (jumlahPemesananTerkait > 0) {
                    System.out.println("✅ Penerbangan dan " + jumlahPemesananTerkait + " pemesanan tiket di penerbangan ini berhasil dihapus!");
                } else {
                    System.out.println("✅ Penerbangan berhasil dihapus!");
                }

            } else if (konfirmasi.equals("n")) {
                System.out.println("❌ Penghapusan dibatalkan.");
                return;
            } else {
                System.out.println("❌ Input tidak valid! Masukkan 'y' atau 'n'.");
            }
        }
    }
}