package br.pucrs.totem.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EdgeDTO {
    private Long id;

    @JsonProperty("aNodeId")
    private Long aNodeId;

    @JsonProperty("bNodeId")
    private Long bNodeId;

    private Double length;
}
