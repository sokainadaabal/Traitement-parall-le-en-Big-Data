package ma.enset.comptecqrses.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DebitedAccountRequestDTO {
    private String accountId;
    private double amount;
    private  String currency;
}
