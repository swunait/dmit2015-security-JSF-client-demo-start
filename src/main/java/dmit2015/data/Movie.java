package dmit2015.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Movie implements Serializable {

    private Long id;

    private String title;

    private LocalDate releaseDate;

    private BigDecimal price;

    private String genre;

    private String rating;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastModifiedDateTime;

    private Integer version;

}