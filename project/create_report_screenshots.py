from pathlib import Path
from PIL import Image, ImageDraw, ImageFont


ROOT = Path(__file__).resolve().parent
ASSETS = ROOT / "report-assets"
ASSETS.mkdir(exist_ok=True)


def font(size, bold=False):
    candidates = [
        r"C:\Windows\Fonts\seguisb.ttf" if bold else r"C:\Windows\Fonts\segoeui.ttf",
        r"C:\Windows\Fonts\arialbd.ttf" if bold else r"C:\Windows\Fonts\arial.ttf",
    ]
    for candidate in candidates:
        path = Path(candidate)
        if path.exists():
            return ImageFont.truetype(str(path), size)
    return ImageFont.load_default()


def wrap_text(draw, text, fnt, max_width):
    words = text.split()
    lines = []
    current = ""
    for word in words:
        proposed = word if not current else f"{current} {word}"
        if draw.textlength(proposed, font=fnt) <= max_width:
            current = proposed
        else:
            if current:
                lines.append(current)
            current = word
    if current:
        lines.append(current)
    return lines


def rounded(draw, xy, radius, fill, outline=None, width=1):
    draw.rounded_rectangle(xy, radius=radius, fill=fill, outline=outline, width=width)


def task_distribution():
    image = Image.new("RGB", (1100, 360), "#100b18")
    draw = ImageDraw.Draw(image)
    title = font(30, True)
    body = font(24)
    small = font(20)

    # Subtle chat-pattern dots.
    for x in range(20, 1100, 70):
        for y in range(18, 360, 55):
            draw.ellipse((x, y, x + 12, y + 12), outline="#2b1f3f")

    rounded(draw, (48, 28, 970, 152), 22, "#8a71e6")
    message = "Всем привет. Я сейчас скину excel файл и инструкции. Решите кто из вас будет делать Use case diagram, а кто class case"
    y = 46
    for line in wrap_text(draw, message, title, 870):
        draw.text((66, y), line, fill="white", font=title)
        y += 38
    draw.text((884, 116), "20:31 ✓✓", fill="#e8ddff", font=small)

    rounded(draw, (650, 170, 1012, 302), 18, "#8a71e6")
    draw.rounded_rectangle((682, 195, 768, 280), radius=8, fill="#f3f4f6")
    draw.polygon([(744, 195), (768, 219), (744, 219)], fill="#d1d5db")
    draw.text((792, 198), "uml.xlsx", fill="white", font=title)
    draw.text((792, 236), "18 KB ·", fill="#f5f1ff", font=body)
    draw.text((922, 264), "20:32 ✓✓", fill="#e8ddff", font=small)

    caption = (
        "Task distribution discussion: the team shared UML materials and agreed who would work on "
        "the use-case and class diagrams."
    )
    draw.text((48, 318), caption, fill="#d9d3e7", font=small)
    image.save(ASSETS / "task_distribution.png")


def first_defense_decision():
    image = Image.new("RGB", (1100, 760), "#100b18")
    draw = ImageDraw.Draw(image)
    title = font(28, True)
    body = font(22)
    small = font(18)

    for x in range(15, 1100, 82):
        for y in range(20, 760, 72):
            draw.arc((x, y, x + 38, y + 38), 20, 310, fill="#2d2142", width=2)

    rounded(draw, (78, 28, 638, 710), 14, "#2b2b2b")
    draw.text((104, 50), "Forwarded from", fill="#34d399", font=small)
    draw.text((104, 74), "nurila", fill="#34d399", font=small)

    # Paper sheet.
    draw.polygon([(118, 118), (595, 98), (604, 650), (106, 670)], fill="#efe7d7")
    draw.line((118, 118, 595, 98, 604, 650, 106, 670, 118, 118), fill="#d6c9b7", width=3)

    pen = "#4b5563"
    draw.text((205, 140), "Core: M", fill=pen, font=body)
    draw.text((384, 140), "Obj / Class", fill=pen, font=body)
    draw.ellipse((152, 210, 352, 392), outline=pen, width=3)
    draw.text((178, 250), "Use cases", fill=pen, font=body)
    draw.line((178, 296, 320, 342), fill=pen, width=3)
    draw.ellipse((390, 205, 560, 350), outline=pen, width=3)
    draw.text((430, 255), "Objs", fill=pen, font=body)
    draw.text((150, 438), "Teacher M", fill=pen, font=body)
    draw.text((320, 438), "Teach course M", fill=pen, font=body)
    draw.line((150, 470, 520, 570), fill=pen, width=3)
    draw.ellipse((175, 500, 305, 612), outline=pen, width=3)
    draw.text((190, 538), "Reflection", fill=pen, font=small)
    draw.text((470, 636), "12:46", fill="white", font=small)

    rounded(draw, (690, 130, 1035, 628), 10, "#ffffff", "#d8dee8")
    draw.text((720, 162), "First defend insights", fill="#0b3445", font=title)
    notes = [
        "During the first defense, the team reviewed the object model and changed several design decisions.",
        "The notes show discussion around use cases, class responsibilities, object interactions, and project structure.",
        "This meeting helped us clarify which parts of the system needed stronger OOP separation.",
    ]
    y = 214
    for note in notes:
        for line in wrap_text(draw, note, body, 285):
            draw.text((720, y), line, fill="#172033", font=body)
            y += 30
        y += 16
    image.save(ASSETS / "first_defense_decision.png")


def program_workflow():
    lines = [
        r"PS C:\Users\danil\CODE\Java\project> java -cp out main.Main",
        "University console",
        "Type: help",
        "Demo logins: login A001 admin_hash | login M001 manager_hash | login T001 prof_hash | login S001 stud_hash",
        "> login T001 prof_hash",
        "Nora Professor logged in.",
        "Current user: Nora Professor",
        "> help",
        "Available commands for Teacher:",
        "help",
        "whoami | logout | save | exit",
        "courses",
        "papers date|citations|pages",
        "top | top-year <year> | top-school-year <SCHOOL> <year>",
        "project <topic>",
        "join <projectNumber> <userId>",
        "",
        "students",
        "mark <studentId> <courseCode> <att1> <att2> <final>",
        "transcript <studentId>",
        "supervisor <studentId> <teacherId>",
        "request <text>",
        "",
        ">",
    ]
    image = Image.new("RGB", (1160, 650), "#1b1d1f")
    draw = ImageDraw.Draw(image)
    mono_path = Path(r"C:\Windows\Fonts\consola.ttf")
    mono = ImageFont.truetype(str(mono_path), 24) if mono_path.exists() else font(22)
    y = 22
    for line in lines:
        draw.text((18, y), line, fill="#f5f5f5", font=mono)
        y += 26
    draw.rectangle((27, y - 20, 37, y + 10), fill="#d1d5db")
    image.save(ASSETS / "program_workflow.png")


if __name__ == "__main__":
    task_distribution()
    first_defense_decision()
    program_workflow()
