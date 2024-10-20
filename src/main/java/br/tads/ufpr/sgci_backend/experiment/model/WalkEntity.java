package br.tads.ufpr.sgci_backend.experiment.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_caminhadas")
public class WalkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link para o vídeo da câmera térmica
    @Column(name = "thermal_camera_video", nullable = false)
    private String thermalCameraVideo;

    // Link para o vídeo de esqueletização
    @Column(name = "skeletonization_video", nullable = false)
    private String skeletonizationVideo;

    // Tamanho do arquivo CSV (em bytes, por exemplo)
    @Column(name = "csv_file_size", nullable = false)
    private long csvFileSize;

    // Data e Hora do registro da caminhada
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    // Observações sobre a caminhada
    @Column(name = "observations", length = 500)
    private String observations;

    // Relacionamento com o experimento (muitas caminhadas para um experimento)
    @ManyToOne
    @JoinColumn(name = "experiment_id", nullable = false)
    private ExperimentEntity experiment;

    // Número da caminhada no experimento (identificar ordem)
    @Column(name = "walk_order", nullable = false)
    private int walkOrder;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThermalCameraVideo() {
        return thermalCameraVideo;
    }

    public void setThermalCameraVideo(String thermalCameraVideo) {
        this.thermalCameraVideo = thermalCameraVideo;
    }

    public String getSkeletonizationVideo() {
        return skeletonizationVideo;
    }

    public void setSkeletonizationVideo(String skeletonizationVideo) {
        this.skeletonizationVideo = skeletonizationVideo;
    }

    public long getCsvFileSize() {
        return csvFileSize;
    }

    public void setCsvFileSize(long csvFileSize) {
        this.csvFileSize = csvFileSize;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public ExperimentEntity getExperiment() {
        return experiment;
    }

    public void setExperiment(ExperimentEntity experiment) {
        this.experiment = experiment;
    }

    public int getWalkOrder() {
        return walkOrder;
    }

    public void setWalkOrder(int walkOrder) {
        this.walkOrder = walkOrder;
    }
}
