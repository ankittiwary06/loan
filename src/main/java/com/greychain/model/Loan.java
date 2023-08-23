package com.greychain.model;



import lombok.Data;
import org.springframework.stereotype.Component;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Loan  implements Serializable {

       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private Integer id;
       @Column
       String loanId;
       @Column
       private BigDecimal amount;
       @Column
       private BigDecimal remainingAmount;
       @Column
       @JsonbDateFormat("dd/MM/yyyy")
       private Date paymentDate;
       @Column
       @JsonbDateFormat("dd/MM/yyyy")
       private Date dueDate;
       @Column
       private Double interest;
       @Column
       private Double penalty;
       @Column
       private boolean cancel;
       @OneToOne
       private Customer customer;
       @OneToOne
       private Lender lender;

}
