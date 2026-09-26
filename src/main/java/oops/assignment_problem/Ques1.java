package oops.assignment_problem;

import java.util.ArrayList;

interface ScoringRule {
    double calculateScore(
            int idea,
            int execution,
            int presentation
    );
}

class InnovationScoring implements ScoringRule {

    @Override
    public double calculateScore(
            int idea,
            int execution,
            int presentation) {

        return (idea * 0.50)
                + (execution * 0.30)
                + (presentation * 0.20);
    }
}

class OpenScoring implements ScoringRule {

    @Override
    public double calculateScore(
            int idea,
            int execution,
            int presentation) {

        return (idea + execution + presentation) / 3.0;
    }
}

class HackathonStudent {
    private String name;

    public HackathonStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class HackathonJudge {
    private String name;

    public HackathonJudge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class HackathonTrack {
    private String name;
    private ScoringRule scoringRule;

    public HackathonTrack(
            String name,
            ScoringRule scoringRule) {

        this.name = name;
        this.scoringRule = scoringRule;
    }

    public String getName() {
        return name;
    }

    public double calculateScore(
            int idea,
            int execution,
            int presentation) {

        return scoringRule.calculateScore(
                idea,
                execution,
                presentation
        );
    }
}

class HackathonProject {
    private String name;

    public HackathonProject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class HackathonScore {
    private HackathonJudge judge;
    private HackathonProject project;
    private int idea;
    private int execution;
    private int presentation;
    private double finalScore;

    public HackathonScore(
            HackathonJudge judge,
            HackathonProject project,
            int idea,
            int execution,
            int presentation,
            double finalScore) {

        this.judge = judge;
        this.project = project;
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.finalScore = finalScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void updateScore(
            int idea,
            int execution,
            int presentation,
            double finalScore) {

        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        this.finalScore = finalScore;
    }
}

class HackathonTeam {
    private String name;
    private ArrayList<HackathonStudent> members;
    private HackathonTrack track;
    private HackathonProject project;
    private ArrayList<HackathonScore> scores;

    public HackathonTeam(
            String name,
            ArrayList<HackathonStudent> members,
            HackathonTrack track) {

        this.name = name;
        this.members = members;
        this.track = track;
        this.scores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<HackathonStudent> getMembers() {
        return members;
    }

    public HackathonTrack getTrack() {
        return track;
    }

    public HackathonProject getProject() {
        return project;
    }

    public void submitProject(HackathonProject project) {

        if (this.project != null) {
            System.out.println(
                    "Submission failed: Team " +
                    name +
                    " can submit only one project."
            );
            return;
        }

        this.project = project;

        System.out.println(
                "Project '" +
                project.getName() +
                "' submitted by " +
                name +
                "."
        );
    }

    public void addScore(HackathonScore score) {
        scores.add(score);
    }

    public ArrayList<HackathonScore> getScores() {
        return scores;
    }
}

class Hackathon {

    private String name;
    private ArrayList<HackathonTeam> teams;
    private String state;

    public Hackathon(String name) {
        this.name = name;
        teams = new ArrayList<>();
        state = "Open";
    }

    public boolean registerTeam(HackathonTeam team) {

        if (!state.equals("Open")) {
            System.out.println(
                    "Registration failed: Hackathon is not open."
            );
            return false;
        }

        int memberCount = team.getMembers().size();

        if (memberCount < 2 || memberCount > 4) {
            System.out.println(
                    "Registration failed: A team must have 2 to 4 members."
            );
            return false;
        }

        for (HackathonTeam existingTeam : teams) {

            for (HackathonStudent student :
                    team.getMembers()) {

                if (existingTeam.getMembers().contains(student)) {
                    System.out.println(
                            "Registration failed: " +
                            student.getName() +
                            " already belongs to another team."
                    );
                    return false;
                }
            }
        }

        teams.add(team);

        System.out.println(
                "Team " +
                team.getName() +
                " registered (" +
                memberCount +
                " members, " +
                team.getTrack().getName() +
                " track)."
        );

        return true;
    }

    public void startJudging() {
        state = "Judging";
    }

    public void publishResults() {
        if (!state.equals("Judging")) {
            System.out.println(
                    "Results cannot be published."
            );
            return;
        }

        state = "Published";

        System.out.println("Results published.");
    }

    public String getState() {
        return state;
    }
}

class HackathonService {

    public void scoreProject(
            Hackathon hackathon,
            HackathonTeam team,
            HackathonJudge judge,
            int idea,
            int execution,
            int presentation) {

        if (hackathon.getState().equals("Published")) {
            System.out.println(
                    "Rescore rejected: Results have already been published."
            );
            return;
        }

        if (team.getProject() == null) {
            System.out.println(
                    "Scoring failed: Team has not submitted a project."
            );
            return;
        }

        double finalScore =
                team.getTrack().calculateScore(
                        idea,
                        execution,
                        presentation
                );

        HackathonScore score =
                new HackathonScore(
                        judge,
                        team.getProject(),
                        idea,
                        execution,
                        presentation,
                        finalScore
                );

        team.addScore(score);

        System.out.println(
                "Score recorded for '" +
                team.getProject().getName() +
                "'."
        );

        System.out.printf(
                "Final score: %.2f%n",
                finalScore
        );
    }
}

public class Ques1 {

    public static void main(String[] args) {

        Hackathon hackathon =
                new Hackathon("Code Sprint");

        HackathonTrack innovation =
                new HackathonTrack(
                        "Innovation",
                        new InnovationScoring()
                );

        HackathonTrack open =
                new HackathonTrack(
                        "Open",
                        new OpenScoring()
                );

        HackathonStudent asha =
                new HackathonStudent("Asha");

        HackathonStudent ravi =
                new HackathonStudent("Ravi");

        HackathonStudent neha =
                new HackathonStudent("Neha");

        HackathonStudent kiran =
                new HackathonStudent("Kiran");

        ArrayList<HackathonStudent> byteBustersMembers =
                new ArrayList<>();

        byteBustersMembers.add(asha);
        byteBustersMembers.add(ravi);
        byteBustersMembers.add(neha);

        HackathonTeam byteBusters =
                new HackathonTeam(
                        "ByteBusters",
                        byteBustersMembers,
                        innovation
                );

        hackathon.registerTeam(byteBusters);

        ArrayList<HackathonStudent> soloMembers =
                new ArrayList<>();

        soloMembers.add(kiran);

        HackathonTeam soloCoder =
                new HackathonTeam(
                        "SoloCoder",
                        soloMembers,
                        open
                );

        hackathon.registerTeam(soloCoder);

        HackathonProject project =
                new HackathonProject("SmartAttend");

        byteBusters.submitProject(project);

        hackathon.startJudging();

        HackathonJudge judge =
                new HackathonJudge("Judge 1");

        HackathonService service =
                new HackathonService();

        service.scoreProject(
                hackathon,
                byteBusters,
                judge,
                8,
                7,
                9
        );

        hackathon.publishResults();

        service.scoreProject(
                hackathon,
                byteBusters,
                judge,
                10,
                7,
                9
        );
    }
}