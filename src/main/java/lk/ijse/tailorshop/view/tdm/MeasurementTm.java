package lk.ijse.tailorshop.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MeasurementTm {
    private String measurementId;
    private double neckSize;
    private double armhole;
    private double sleeveLength;
    private double wrist;
    private double chest;
    private double torsoLength;
    private double waist;
    private double hip;
    private double crotchLength;
    private double shoulderLength;
    private double thighCircumference;
    private double waistToHem;
    private String employeeId;
    private String customerId;
}
