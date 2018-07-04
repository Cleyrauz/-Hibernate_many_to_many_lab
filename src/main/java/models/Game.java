package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="games")

public class Game {

    private int id;
    private String title;
    private GenreType genreType;
    private List<Console> consoles;
    private List<Owner> ownerFavourite;

    public Game(){

    }

    public Game(String title, GenreType genreType) {
        this.title = title;
        this.genreType = genreType;
        this.consoles = new ArrayList<Console>();
        this.ownerFavourite = new ArrayList<Owner>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(value = EnumType.STRING)
    public GenreType getGenreType() {
        return genreType;
    }

    public void setGenreType(GenreType genreType) {
        this.genreType = genreType;
    }

    @ManyToMany
    @JoinTable(name="console_game", joinColumns = {@JoinColumn(name="game_id", nullable = false,
            updatable = false)}, inverseJoinColumns = {@JoinColumn(name="console_id", nullable = false,
            updatable = false)})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    public void addConsoles(Console console){
        this.consoles.add(console);
    }

    @OneToMany(mappedBy = "favouriteGame", fetch = FetchType.LAZY)
    public List<Owner> getOwnerFavourite() {
        return ownerFavourite;
    }

    public void setOwnerFavourite(List<Owner> ownerFavourite) {
        this.ownerFavourite = ownerFavourite;
    }
}
