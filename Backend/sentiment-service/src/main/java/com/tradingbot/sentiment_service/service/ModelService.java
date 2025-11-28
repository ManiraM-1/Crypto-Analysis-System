/*package com.tradingbot.sentiment_service.service;

import com.tradingbot.sentiment_service.dto.MlPredictionRequestDTO;
import com.tradingbot.sentiment_service.dto.MlPredictionResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ModelService {

    private final WebClient webClient;

    public ModelService(WebClient.Builder webClientBuilder) {

        // --- THIS IS THE CRITICAL PART ---
        //
        // PASTE YOUR NGROK URL (from Colab) HERE
        // It MUST start with "https://" and end with a "/"
        //
        // EXAMPLE: "https://sleekly-unswaddling-karlyn.ngrok-free.dev/"
        //
        String ngrokUrl = "https://sleekly-unswaddling-karlyn.ngrok-free.dev/";

        this.webClient = webClientBuilder.baseUrl(ngrokUrl).build();
    }

    /**
     * Calls the Python API in Google Colab to get a prediction.
     * @param symbol The coin symbol (e.g., "BTC/USDT")
     * @param minutes The time horizon (e.g., 240)
     * @return A Mono of the model's response
     *//*
    public Mono<MlPredictionResponseDTO> getPrediction(String symbol, int minutes) {
        System.out.println("Asking Python (Colab) API for prediction for " + symbol + "...");

        // 1. Create the request body object
        MlPredictionRequestDTO requestBody = new MlPredictionRequestDTO(symbol, minutes);

        // 2. Make the POST request
        return this.webClient.post()
                .uri("predict") // This is the endpoint, e.g., "https://...ngrok.io/predict"
                .bodyValue(requestBody) // Send the {symbol, minutes} JSON
                .retrieve()
                .bodyToMono(MlPredictionResponseDTO.class) // Map the JSON response to our DTO
                .doOnSuccess(response ->
                        System.out.println("Python API returned: " + (response.getPredicted_signal() == 1 ? "UP" : "DOWN"))
                )
                .onErrorResume(e -> {
                    // This is a "fail-safe"
                    // If the Colab notebook is down, it returns a safe "HOLD" signal
                    System.err.println("PYTHON API IS DOWN or failed. Error: " + e.getMessage());
                    System.err.println("Returning a STUBBED 'HOLD' prediction.");
                    // We create a fake "DOWN" (0) response with 50% confidence
                    return Mono.just(new MlPredictionResponseDTO(0, 50.0, 0.0, symbol, "API_DOWN"));
                });
    }
}
*/

package com.tradingbot.sentiment_service.service;

import com.tradingbot.sentiment_service.dto.MlPredictionRequestDTO;
import com.tradingbot.sentiment_service.dto.MlPredictionResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ModelService {

    private final WebClient webClient;

    public ModelService(WebClient.Builder webClientBuilder) {
        // PASTE YOUR NGROK URL HERE
        // Example: "https://sleekly-unswaddling-karlyn.ngrok-free.dev/"
        String ngrokUrl = "https://5e20c33a22da.ngrok-free.app/";

        this.webClient = webClientBuilder.baseUrl(ngrokUrl).build();

        System.out.println("✅ ModelService initialized with URL: " + ngrokUrl);
    }

    public Mono<MlPredictionResponseDTO> getPrediction(String symbol, int minutes) {
        System.out.println("🔮 Requesting ML prediction for " + symbol + " (" + minutes + " minutes)");

        MlPredictionRequestDTO requestBody = new MlPredictionRequestDTO(symbol, minutes);

        return this.webClient.post()
                .uri("predict")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(MlPredictionResponseDTO.class)
                .doOnSuccess(response -> {
                    if ("success".equals(response.getStatus())) {
                        System.out.println("✅ ML Prediction: " + response.getPrediction()
                                + " (Confidence: " + String.format("%.2f%%", response.getConfidenceScore()) + ")");
                    } else {
                        System.out.println("⚠️  ML API returned non-success status");
                    }
                })
                .onErrorResume(e -> {
                    System.err.println("❌ PYTHON API ERROR: " + e.getMessage());
                    System.err.println("   Returning safe HOLD signal");

                    // Create a fallback response
                    MlPredictionResponseDTO fallback = new MlPredictionResponseDTO();
                    fallback.setSymbol(symbol);
                    fallback.setPrediction("HOLD");
                    fallback.setProbabilityUp(0.5);
                    fallback.setProbabilityDown(0.5);
                    fallback.setConfidence(0.0);
                    fallback.setCurrentPrice(0.0);
                    fallback.setStatus("error");
                    fallback.setError("API_DOWN: " + e.getMessage());

                    return Mono.just(fallback);
                });
    }
}
