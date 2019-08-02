package com.stackroute.muzixApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {
    @Id
    int id;
    String name;
    String comment;
}
