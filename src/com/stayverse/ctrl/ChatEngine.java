package com.stayverse.ctrl;

import java.util.Random;

public class ChatEngine {
    private final String[] res = {
        "I can help you find luxury stays in Paris or Tokyo!",
        "Stays with ratings above 4.8 are trending right now.",
        "Check out our Genius loyalty rewards for big discounts."
    };
    public String getResponse(String input) {
        if (input.toLowerCase().contains("paris")) return "Paris is beautiful! Try the Ritz Paris.";
        return res[new Random().nextInt(res.length)];
    }
}
