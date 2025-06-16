package com.gameLib.GameLib;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import com.gameLib.GameLib.model.Review;
import com.gameLib.GameLib.dto.ReviewDTO;
import com.gameLib.GameLib.repository.ReviewRepository;
import com.gameLib.GameLib.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.gameLib.GameLib.dto.GameAverageRatingDTO;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsRestController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @GetMapping
    public List<Object> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(r -> {
            return new java.util.HashMap<String, Object>() {{
                put("id", r.getId());
                put("gameName", r.getGameName());
                put("rating", r.getRating());
                put("comment", r.getComment());
                put("createdAt", r.getCreatedAt().toString());
            }};
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            Review review = new Review();
            review.setId(idGeneratorService.generateReviewId());
            review.setGameName(reviewDTO.getGameName());
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());
            review.setCreatedAt(java.time.LocalDateTime.now());
            reviewRepository.save(review);
            return ResponseEntity.ok("Review added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving review: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable String id) {
        try {
            Review review = reviewRepository.findById(id).orElse(null);
            if (review == null) {
                return ResponseEntity.notFound().build();
            }
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            java.time.Duration duration = java.time.Duration.between(review.getCreatedAt(), now);
            if (duration.toHours() > 24) {
                return ResponseEntity.status(403).body("Review can only be deleted within 24 hours of creation.");
            }
            reviewRepository.delete(review);
            return ResponseEntity.ok("Review deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting review: " + e.getMessage());
        }
    }

    @GetMapping("/average")
    public double getAverageRating() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Review r : reviews) {
            sum += r.getRating();
        }
        return sum / reviews.size();
    }

    @GetMapping("/average-per-game")
    public List<GameAverageRatingDTO> getAverageRatingPerGame() {
        List<Review> reviews = reviewRepository.findAll();
        java.util.Map<String, java.util.List<Review>> reviewsByGame = new java.util.HashMap<>();
        for (Review r : reviews) {
            String gameName = r.getGameName();
            if (!reviewsByGame.containsKey(gameName)) {
                reviewsByGame.put(gameName, new java.util.ArrayList<>());
            }
            reviewsByGame.get(gameName).add(r);
        }
        java.util.List<GameAverageRatingDTO> averages = new java.util.ArrayList<>();
        for (java.util.Map.Entry<String, java.util.List<Review>> entry : reviewsByGame.entrySet()) {
            String gameName = entry.getKey();
            java.util.List<Review> gameReviews = entry.getValue();
            double sum = 0.0;
            for (Review r : gameReviews) {
                sum += r.getRating();
            }
            double average = gameReviews.isEmpty() ? 0.0 : sum / gameReviews.size();
            averages.add(new GameAverageRatingDTO(gameName, average));
        }
        return averages;
    }
}