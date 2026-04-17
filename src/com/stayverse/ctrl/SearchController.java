package com.stayverse.ctrl;

import com.stayverse.model.*;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    public List<AbstractProperty> getAllProperties() {
        List<AbstractProperty> list = new ArrayList<>();
        
        // --- VARANASI (AS REQUESTED) ---
        Hotel hV = new Hotel("HV1", "Perfectstayz Premium Kaashin", "Varanasi", 1858, 4.8, "Close to Kashi Vishwanath Temple 200 meters from Ganga Ghat with Ganga Aarti and Hi Tea.");
        hV.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_varanasi_premium_gallery_1_1776388679621.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_varanasi_premium_gallery_1_1776388679621.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_varanasi_premium_gallery_1_1776388679621.png"
        ));
        hV.setAmenities(List.of("Complimentary Hi-Tea", "Late Check-Out", "Enjoy Aarti Ceremony", "Non-Refundable"));
        list.add(hV);

        // --- PARIS ---
        Hotel h1 = new Hotel("H1", "Ritz Paris", "Paris", 1200, 4.9, "Timeless French elegance in the heart of the Place Vendome.");
        h1.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_ritz_paris_gallery_1_1776387333807.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_ritz_paris_gallery_2_1776388409380.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_ritz_paris_gallery_3_1776388435308.png"
        ));
        list.add(h1);
        
        // --- TOKYO ---
        Resort r1 = new Resort("R1", "Aman Tokyo", "Tokyo", 1500, 5.0, "A sanctuary at the top of the Otemachi Tower, blending urban energy with Zen calm.");
        r1.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_aman_tokyo_gallery_1_1776387357067.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_aman_tokyo_gallery_1_1776387357067.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_aman_tokyo_gallery_1_1776387357067.png"
        ));
        list.add(r1);

        // --- NEW YORK ---
        Hotel h2 = new Hotel("H2", "Baccarat Hotel", "New York", 1100, 4.8, "A Midtown Manhattan masterpiece of crystal and light.");
        h2.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_baccarat_ny_gallery_1_1776387377837.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_baccarat_ny_gallery_1_1776387377837.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_baccarat_ny_gallery_1_1776387377837.png"
        ));
        list.add(h2);

        // --- LONDON ---
        Hotel h3 = new Hotel("H3", "The Savoy", "London", 950, 4.7, "The first luxury hotel in Britain, offering unparalleled Edwardian splendor.");
        h3.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_savoy_london_gallery_1_1776387441040.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_savoy_london_gallery_1_1776387441040.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_savoy_london_gallery_1_1776387441040.png"
        ));
        list.add(h3);

        // --- DUBAI ---
        Resort r2 = new Resort("R2", "Burj Al Arab", "Dubai", 2500, 5.0, "The world's most opulent hotel, an architectural marvel on its own island.");
        r2.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_burj_dubai_gallery_1_1776387461855.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_burj_dubai_gallery_1_1776387461855.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_burj_dubai_gallery_1_1776387461855.png"
        ));
        list.add(r2);

        // --- MALDIVES ---
        Resort r3 = new Resort("R3", "Soneva Jani", "Maldives", 3200, 5.0, "The absolute pinnacle of overwater luxury living.");
        r3.setImages(List.of(
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_maldives_villa_gallery_1_1776387914246.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_maldives_villa_gallery_1_1776387914246.png",
            "C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_maldives_villa_gallery_1_1776387914246.png"
        ));
        list.add(r3);

        return list;
    }
    public List<AbstractProperty> search(String q) {
        return getAllProperties().stream()
            .filter(p -> p.getName().toLowerCase().contains(q.toLowerCase()) || p.getCity().toLowerCase().contains(q.toLowerCase()))
            .toList();
    }
}
