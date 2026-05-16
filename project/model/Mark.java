package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Mark implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double attestation1;
    private double attestation2;
    private double finalExam;
    private double total;

    public Mark() {
        this(0, 0, 0);
    }

    public Mark(double attestation1, double attestation2, double finalExam) {
        validatePart("First attestation", attestation1, 30);
        validatePart("Second attestation", attestation2, 30);
        validatePart("Final exam", finalExam, 40);
        this.attestation1 = attestation1;
        this.attestation2 = attestation2;
        this.finalExam = finalExam;
        calculateTotal();
    }

    public double getAttestation1() {
        return attestation1;
    }

    public void setAttestation1(double attestation1) {
        validatePart("First attestation", attestation1, 30);
        this.attestation1 = attestation1;
        calculateTotal();
    }

    public double getAttestation2() {
        return attestation2;
    }

    public void setAttestation2(double attestation2) {
        validatePart("Second attestation", attestation2, 30);
        this.attestation2 = attestation2;
        calculateTotal();
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        validatePart("Final exam", finalExam, 40);
        this.finalExam = finalExam;
        calculateTotal();
    }

    public double getTotal() {
        return total;
    }

    public void calculateTotal() {
        this.total = attestation1 + attestation2 + finalExam;
    }

    public boolean isFailed() {
        return total < 50;
    }

    private void validatePart(String partName, double value, double maxValue) {
        if (value < 0 || value > maxValue) {
            throw new IllegalArgumentException(partName + " must be between 0 and " + maxValue + ".");
        }
    }

    @Override
    public String toString() {
        return "Mark{" +
                "attestation1=" + attestation1 +
                ", attestation2=" + attestation2 +
                ", finalExam=" + finalExam +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mark mark)) {
            return false;
        }
        return Double.compare(attestation1, mark.attestation1) == 0
                && Double.compare(attestation2, mark.attestation2) == 0
                && Double.compare(finalExam, mark.finalExam) == 0
                && Double.compare(total, mark.total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attestation1, attestation2, finalExam, total);
    }
}
