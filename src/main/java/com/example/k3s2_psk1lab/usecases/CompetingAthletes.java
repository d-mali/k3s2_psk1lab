package com.example.k3s2_psk1lab.usecases;

import com.example.k3s2_psk1lab.interceptors.LoggedInvocation;
import com.example.k3s2_psk1lab.persistence.AthletesDAO;
import com.example.k3s2_psk1lab.persistence.CompetitionsDAO;
import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.entities.Competition;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Model
public class CompetingAthletes {

    @Inject
    private AthletesDAO athletesDAO;

    @Getter @Setter
    private Competition competitionToUpdate = new Competition();

    @Inject
    private CompetitionsDAO competitionsDAO;

    @Getter @Setter
    private Athlete selectedAthlete = new Athlete();

    @Getter @Setter
    private String amount;

    @Getter @Setter
    private Integer amountCalculated;

    @Getter @Setter
    private Integer amountInput;

    public void setAmountCalculated(){
        amountCalculated = this.allParticipants.size();
        try {
            amountInput = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + amount);
            amountInput = 0;
        }
    }


    @Getter @Setter
    private Competition competition = new Competition();

    @Getter
    private List<Athlete> allParticipants;

    @Transactional
    public void updateCompetition() {
        competition = this.competitionsDAO.update(competition.getId(), competitionToUpdate.getName());
    }

    @Transactional
    public void createActiveAthlete() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId  = Long.parseLong(requestParameters.get("competitionId"));
//        Long athleteId      = Long.parseLong(requestParameters.get("athleteId"));

        this.selectedAthlete = this.athletesDAO.findOne(selectedAthlete.getId());
        this.competition = this.competitionsDAO.findOne(competitionId);

        List<Athlete> athletes = this.competition.getAthletes();
        athletes.add(this.selectedAthlete);
        this.competition.setAthletes(athletes);
        competition = this.competitionsDAO.updateAthletes(this.competition);
    }

    private void loadAllParticipants(){ this.allParticipants = competition.getAthletes(); }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId = Long.parseLong(requestParameters.get("competitionId"));
        this.competition = competitionsDAO.findOne(competitionId);

        loadAllParticipants();
    }
}