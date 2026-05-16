Start with:
java -cp out main.Main


# University System

This project models a university system with authenticated users, academic course registration, marks, reports, employee messaging, news notifications, and research management.

## Main Roles

- `Admin`: manages users and views action logs.
- `Manager`: approves registrations, assigns courses, manages news, creates academic reports, views sorted users, and reviews signed employee requests.
- `Teacher`: views assigned courses/students, puts marks, sends complaints, receives ratings, and may be a researcher.
- `Student`: bachelor by default, registers for courses, views marks/transcript, rates teachers, and may be a researcher.
- `ResearchEmployee`: an employee who is a researcher but is neither a teacher nor a student.

## Research

`Researcher` is modeled as an interface. Teachers and students receive a `DefaultResearcher` role when they become researchers. Professors automatically become researchers.

Research papers include title, authors, journal, citations, page count, DOI, and publication date. Papers can be sorted by publication date, citations, title, or article length. The system can also print all researchers' papers and find top cited researchers overall, by school, by year, or by school and year.

Fourth-year students can have a research supervisor. Assigning a supervisor with h-index lower than 3 throws `LowHIndexException`.

Research projects contain a topic, papers, and researcher participants. Adding a non-researcher throws `NotAResearcherException`.

## Academic Rules

- Course lessons support `LECTURE` and `PRACTICE`.
- Courses may have more than one instructor.
- Courses can be limited to a specific major and year.
- Students cannot register for more than 21 credits.
- Marks consist of first attestation, second attestation, and final exam.
- Mark parts are validated against 30/30/40 points.
- A student cannot fail the same course more than 3 times.

## Design Patterns

- Singleton: `Database`.
- Factory: `UserFactory`.
- Observer: `News`, `Observer`, and `User`.
- Strategy: paper, student, and teacher comparators.
- Role Object/Decorator-like composition: `DefaultResearcher` adds researcher behavior to students and teachers.

## Persistence

`Database` uses Java object serialization and stores users, courses, research projects, news, and employee requests.
