# TicTacToe Game - UI Enhanced Prompts for Stitch Tool

## VERSION 1: Modern Glassmorphism & Gradient Design

### Full UI Specification for TicTacToe Game Enhancement

**Overall Design Philosophy:**
Modernize the entire TicTacToe game with a glassmorphism aesthetic combined with vibrant gradient backgrounds. Use semi-transparent frosted glass effects, smooth animations, and a premium feel throughout all screens.

---

### Screen 1: Home Screen - Game Mode Selection

**Background & Layout:**

- Primary background: Animated gradient (top-left to bottom-right) using deep purple (#2D1B4E) to midnight blue (#0F1C3A)
- Add subtle animated gradient overlay with accent colors (cyan #00D9FF fading to pink #FF006E)
- Full-screen responsive layout with statusBar and navigationBar padding
- Safe area handling for notch devices

**Top Section - Dark Mode Toggle:**

- Position in top-right corner with 24.dp horizontal padding
- "Dark Mode" label: 16.sp, SemiBold, white text
- Material 3 switch with custom styling:
  - Checked state: Neon cyan glow (#00D9FF) with shadow
  - Unchecked state: Light gray with subtle animation
  - Add 4dp shadow for elevation effect

**Title Section:**

- "Tic Tac Toe" text: 50.sp, cursive font family, bold weight
- Color: Vibrant cyan (#00D9FF) with text shadow (4dp blur, purple shadow)
- Add gentle floating animation (±2dp vertical movement, 3-second loop)
- Subtitle: "Select Game Mode" in 28.sp bold, white color

**Two-Player Button:**

- Background: Linear gradient from cyan (#00D9FF) to light blue (#00B4D8)
- Size: 0.85 of screen width, 62.dp height
- Corner radius: 16.dp with 8.dp outer shadow
- Text: "Two Player" in 20.sp bold, white
- Interactive: Scale up on press (1.05x), shadow expands on hover
- Ripple animation on tap

**AI Difficulty Selection Section:**

- Divider text: "vs AI - Select Difficulty" in 18.sp, white, centered
- Vertical spacing: 12.dp between buttons

**Difficulty Buttons (Easy, Medium, Hard):**

_Easy Button:_

- Background: Linear gradient from lime green (#00D600) to emerald (#00D084)
- Text: "Easy" in 18.sp bold, white
- Size: 0.85 screen width, 60.dp height
- Corner radius: 16.dp
- Shadow: 6.dp with green tint
- Animation: Subtle green glow on hover

_Medium Button:_

- Background: Linear gradient from orange (#FF9800) to deep orange (#FF6F00)
- Text: "Medium" in 18.sp bold, white
- Size: 0.85 screen width, 60.dp height
- Corner radius: 16.dp
- Shadow: 6.dp with orange tint
- Animation: Pulse effect (scale 1.0 → 1.02, 2-second loop)

_Hard Button:_

- Background: Linear gradient from red (#FF5252) to deep red (#D32F2F)
- Text: "Hard" in 18.sp bold, white
- Size: 0.85 screen width, 60.dp height
- Corner radius: 16.dp
- Shadow: 6.dp with red tint
- Animation: Red glow on hover, slight rotation on press

**Responsive Adjustments:**

- For devices < 360dp width or < 700dp height: Reduce padding by 30%, font sizes by 15%, button heights to 56.dp

---

### Screen 2: Gameplay Screen

**Background & Layout:**

- Same base gradient as home screen (purple to dark blue)
- Full screen with transparent safety padding
- Three-zone vertical layout: Top status area, Central game board, Bottom turn indicator

**Top Status Area (Safe Area):**

- Position: Below statusBar, 24.dp top padding
- Layout: Row with title on left, score on right

_Title Section:_

- "Tic Tac Toe": 42.sp, cursive font, cyan color (#00D9FF)
- Subtle text shadow for depth

_Score Display - ScoreBlock Component:_

- Layout: Row with score items
- Current player label: "Player X Score:" in 14.sp medium weight, white
- Score number: "5" in 22.sp bold, cyan glow
- Vertical divider: 2.dp width, 28.dp height, dark gray
- Opponent label: "Player O Score:" in 14.sp medium weight, white
- Opponent score: "3" in 22.sp bold, orange glow
- Background: Glassmorphism effect - semi-transparent dark overlay with 8.dp blur
- Corner radius: 12.dp
- Padding: 12.dp internal, 24.dp horizontal margin
- Shadow: 4.dp drop shadow

**Central Game Board:**

- Container size: Constrained 3:2 aspect ratio (wider than tall for better gameplay)
- Background: Slight gradient from dark navy to darker navy
- Border: 3.dp solid cyan (#00D9FF) with glow effect
- Corner radius: 16.dp

_Board Grid - 3x3 LazyVerticalGrid:_

- Grid spacing: 12.dp between cells
- Cell background: Dark gradient overlay
- Cell border: 1.dp subtle cyan accent
- Corner radius: 12.dp per cell

_Board Cell Components (Circle/Cross):_

- Circle (O):
  - Stroke: 8.dp in cyan (#00D9FF)
  - Animation: Scale in from 0 to 1 (300ms tween)
  - Glow effect: 1.5dp outer shadow
  - Size: 70% of cell
- Cross (X):
  - Stroke: 8.dp in pink/magenta (#FF006E)
  - Animation: Progressive stroke drawing (300ms)
  - Glow effect: 2dp shadow
  - Lines centered in cell
  - Size: 65% of cell

- Winning Line:
  - Stroke color: Rainbow gradient (cyan → pink → yellow)
  - Stroke width: 10.dp
  - Animation: Draw animation (400ms with scaleIn)
  - Shadow: Glow effect with animation

_Interactive Cell Feedback:_

- Empty cell on hover: Scale to 1.08x with border glow
- Ripple animation on tap
- Haptic feedback: Medium intensity

**Bottom Turn Indicator:**

- Position: Below board, 16.dp margin
- Text: "Current Turn: X" or "AI is thinking..." in 18.sp bold
- Dynamic color based on current player (cyan for X, orange for O)
- Animation: Fade in/out pulse effect when it's AI's turn
- Background: Semi-transparent rounded container
- Corner radius: 12.dp
- Padding: 12.dp

**Responsive Behavior:**

- For small screens: Reduce top padding, scale board proportionally
- For large screens: Add more padding, increase spacing
- Board always centered and maintains aspect ratio

---

### Screen 3: Game Over Bottom Sheet

**Bottom Sheet Container:**

- Position: Slides up from bottom
- Background: Glassmorphism - semi-transparent dark background (0.95 opacity) with blur backdrop
- Corner radius: 28.dp top corners (rounded top only)
- No drag handle
- Animation: Slide up with spring physics (200ms)

**Content Layout - Centered Column:**

- Horizontal padding: 24.dp
- Vertical padding: 24.dp
- Vertical spacing: 16.dp between elements
- All content center-aligned

**Header - "Game Over" Text:**

- Size: 28.sp bold
- Color: Bright cyan (#00D9FF) with text shadow
- Additional glow effect underneath
- Animation: Scale up with delay (entry animation)

**Result Message:**

- Conditional text:
  - If winner: "Player X Wins! 🎉" or "You Win! 🎉" in 20.sp medium
  - If draw: "It's a Draw!" in 20.sp medium
- Color: White text
- Animation: Fade in with slight scale

**Play Again Button:**

- Width: 86% of sheet width
- Height: 58.dp
- Corner radius: 22.dp
- Background: Linear gradient cyan to light blue (#00D9FF → #00B4D8)
- Text color: White, 17.sp semi-bold
- Shadow: 8.dp beneath button
- Interactive state:
  - Default: Glow effect
  - Pressed: Scale 0.95x with expanded shadow
  - Animation: Ripple on tap

**Exit Button:**

- Width: 86% of sheet width
- Height: 58.dp
- Corner radius: 22.dp
- Background: Linear gradient red (#FF5252 → #D32F2F)
- Text color: White, 17.sp semi-bold
- Shadow: 8.dp
- Interactive state:
  - Default: Subtle hover effect
  - Pressed: Scale 0.95x
  - Animation: Ripple feedback

**Spacing Between Buttons:** 12.dp

---

### Screen 4: Exit Confirmation Bottom Sheet

**Sheet Design - Same as Game Over:**

- Glassmorphism background with blur
- 28.dp rounded top corners
- 24.dp padding all around
- Centered column layout

**Header - "Exit Current Game" Text:**

- Size: 28.sp bold
- Color: Bright cyan (#00D9FF)
- Text shadow for depth

**Confirmation Message:**

- Text: "Are you sure you want to exit this game?"
- Size: 18.sp medium weight
- Color: White
- Animation: Fade in

**Exit Button:**

- 86% width, 58.dp height
- Background: Cyan gradient (#00D9FF → #00B4D8)
- Text: "Exit" in 17.sp semi-bold white
- Shadow: 8.dp
- Interaction: Full scale feedback

**Cancel Button:**

- 86% width, 58.dp height
- Background: Red gradient (#FF5252 → #D32F2F)
- Text: "Cancel" in 17.sp semi-bold white
- Shadow: 8.dp
- Interaction: Full scale feedback

**Vertical spacing between buttons:** 12.dp

---

### Global UI Elements & Animations

**Color Palette:**

- Primary: Cyan (#00D9FF)
- Secondary: Pink/Magenta (#FF006E)
- Accent Green (Easy): #00D600
- Accent Orange (Medium): #FF9800
- Accent Red (Hard): #FF5252
- Background Dark: #0F1C3A
- Background Gray: #1A1A2E
- Text Primary: #FFFFFF
- Text Secondary: #B0B0B0

**Typography:**

- Font Family: System default + Cursive for title
- Heading: 28.sp - 50.sp, bold weight
- Body: 16.sp - 20.sp, medium weight
- Small: 14.sp - 15.sp, semi-bold

**Animations Throughout:**

- All transitions: 200-300ms duration
- Easing: Cubic bezier for smooth curves
- Scale feedback: 1.05x on interactive elements
- Glow effects: 2-4.dp shadows with color-matched blur
- Ripple animations: Material 3 standard ripple

**Dark Mode Support:**

- In light mode: Lighter background gradients, darker text
- In dark mode: Deeper backgrounds, brighter text
- Toggle switching: Smooth 300ms transition animation

---

## VERSION 2: Neumorphism with Soft Shadows & Minimalist Elegance

### Full UI Specification - Clean Minimalist Approach

**Overall Design Philosophy:**
Implement a soft neumorphic design system with subtle embossed/debossed effects, muted color palette, and minimal visual hierarchy. Focus on clean typography and soft shadows for a premium, sophisticated feel.

---

### Screen 1: Home Screen - Minimalist Layout

**Background:**

- Solid soft beige (#F5F5F7) for light mode
- Solid soft dark gray (#1D1D1F) for dark mode
- No gradients - pure, clean background

**Safe Area Handling:**

- 24.dp padding all sides
- Full responsive width consideration

**Top Section - Dark Mode Toggle:**

- Horizontal row layout
- Switch styling with neumorphic treatment:
  - Track background: Soft convex neumorphic surface
  - Thumb: Subtle shadow creating raised effect
  - Smooth 300ms transition
- Text "Dark Mode": 16.sp, medium weight, muted text color

**Title Section:**

- "Tic Tac Toe": 48.sp, regular font family (clean sans-serif)
- Color: Soft neutral gray (#404040)
- No shadow, pure typography
- Below: "Select Game Mode" in 24.sp, medium weight, lighter gray (#666666)

**Two-Player Button:**

- Style: Neumorphic raised surface
- Background: Same as primary background with raised effect
- Flat, simple text: "Two Player"
- Size: 0.85 width, 56.dp height
- Corner radius: 12.dp (soft curves)
- Shadow: Soft double shadow (top-left light, bottom-right dark)
- Color: Muted teal (#66999A)
- No gradients, no complex effects

**AI Difficulty Section:**

- Divider text: "vs AI - Select Difficulty", simple 16.sp

**Difficulty Buttons:**

- All with same neumorphic raised treatment
- Three buttons stacked vertically with 12.dp spacing

_Easy Button:_

- Muted green (#7A9E7F)
- Text: "Easy", 16.sp medium

_Medium Button:_

- Muted gold (#A39A64)
- Text: "Medium", 16.sp medium

_Hard Button:_

- Muted red (#A37070)
- Text: "Hard", 16.sp medium

- Interactive: Subtle press effect (slight inset shadow on tap)
- All buttons: 0.85 width, 56.dp height, 12.dp corner radius

---

### Screen 2: Gameplay Screen - Clean Minimalist

**Background:**

- Soft gradient (very subtle - barely perceptible)
- Light mode: #F8F8F8 to #F5F5F5
- Dark mode: #1F1F1F to #1D1D1D

**Status Area:**

- Title: "Tic Tac Toe", 36.sp regular, muted color
- Score display in separate neumorphic cards:
  - Two columns: Player X | Player O
  - Each card: Soft neumorphic inset effect
  - Labels: 13.sp, muted gray
  - Scores: 20.sp bold, neutral text color
  - Spacing: 16.dp between cards
  - Corner radius: 12.dp

**Game Board:**

- 3x3 grid with minimal styling
- Cell background: Flat neutral color (slightly lighter than page)
- Cell borders: 1.dp light gray, very subtle
- Corner radius: 8.dp per cell
- Spacing: 10.dp gaps

_Circle/Cross Components:_

- Circle (O): Stroke 7.dp, soft blue (#6B8E8E)
- Cross (X): Stroke 7.dp, soft charcoal (#505050)
- No animations or glows
- Simple, clean geometry
- Size: 65% of cell

**Bottom Turn Indicator:**

- Simple text: "Current Turn: X" or "AI thinking..."
- 17.sp medium weight
- Muted text color
- Light neutral background container
- Corner radius: 10.dp
- Minimal padding (10.dp)
- No animation - static or simple fade

---

### Screen 3: Game Over Bottom Sheet

**Sheet Presentation:**

- Slides up smoothly (200ms ease)
- Background: Frosted glass effect (subtle blur only)
- Corner radius: 24.dp top
- Padding: 28.dp

**Content - Centered Column:**

- Header "Game Over": 26.sp regular weight (not bold)
- Result message: 18.sp medium weight
- Vertical spacing: 18.dp between elements

**Play Again Button:**

- 0.86 width, 56.dp height
- Neumorphic raised surface
- Soft teal color (#66999A)
- Corner radius: 14.dp
- Simple shadow treatment
- Text: 16.sp medium, muted text

**Exit Button:**

- 0.86 width, 56.dp height
- Neumorphic raised surface
- Soft red color (#A37070)
- Corner radius: 14.dp
- Shadow treatment matches play again
- Text: 16.sp medium, muted text

**Button spacing:** 14.dp

---

### Screen 4: Exit Confirmation Bottom Sheet

**Design:** Matches game-over sheet styling

- Header: "Exit Current Game" (26.sp regular)
- Message: "Are you sure you want to exit this game?" (17.sp medium)
- Exit button: Soft raised neumorphic, teal (#66999A), 56.dp height
- Cancel button: Soft raised neumorphic, muted gray (#808080), 56.dp height
- Spacing: 14.dp between buttons

---

### Global UI Elements - Minimalist System

**Color Palette (Muted & Sophisticated):**

- Primary Modern: #66999A (soft teal)
- Secondary Color: #A39A64 (soft gold)
- Accent Alert: #A37070 (soft red)
- Success/Easy: #7A9E7F (soft green)
- Background Light: #F5F5F7
- Background Dark: #1D1D1F
- Text Primary: #404040 (light) / #E8E8E8 (dark)
- Text Secondary: #808080

**Typography - All Sans-Serif:**

- Heading: 24-48.sp, regular weight
- Body: 16-17.sp, medium weight
- Small: 13-15.sp, regular weight
- No italics or special styles

**Shadows - Neumorphic Style:**

- Light shadow: 2-3dp offset (+1dp, +1dp), very light gray
- Dark shadow: 2-3dp offset (-1dp, -1dp), subtle dark gray
- Combined for raised/embossed effect
- No harsh shadows or glows

**Interactions:**

- All elements: Subtle press feedback (slight inset effect)
- Transitions: 150-200ms ease for all state changes
- No ripples or complex animations
- Simple scale feedback (0.98x on press)

**Spacing Consistency:**

- Component spacing: 12-16.dp
- Content padding: 20-28.dp
- Button sizing: Consistent 56.dp height

---

## VERSION 3: Neo-Brutalism with Bold Typography & High Contrast

### Full UI Specification - Bold, Unconventional Design

**Overall Design Philosophy:**
Bold neo-brutalist approach with strong typography, high contrast, boxy shapes, maximalist color blocking, and unconventional layout. Raw cement textures, chunky elements, and statement-making design choices. Modern yet rebellious aesthetic.

---

### Screen 1: Home Screen - Neo-Brutalist Bold

**Background:**

- Primary layer: Solid black (#000000)
- Optional texture: Fine concrete/fabric texture overlay at 3% opacity
- Accent band: 6.dp horizontal stripe in electric yellow (#FFFF00) positioned 30% from top

**Safe Area & Layout:**

- Aggressive padding: 32.dp on sides
- Raw, asymmetrical spacing

**Top Section - Dark Mode Toggle:**

- Positioned absolutely in top-left corner
- Switch track: Heavy 4.dp border, yellow (#FFFF00) on dark background
- Text "DARK MODE": 14.sp, ALL CAPS, bold weight, yellow
- Label positioned above switch (unconventional placement)

**Title Section:**

- "Tic Tac Toe": 64.sp, ultra-bold, ALL CAPS typography
- Color: Electric yellow (#FFFF00) with black 2.dp text outline
- Kerning: Tight, aggressive letter spacing
- Below title: Chunky divider line 8.dp height, pure black with yellow border

**Section Divider:**

- Thick colored line: 4.dp height
- "SELECT GAME MODE" text overlaid on line, reversed out (black text on white/yellow)
- 18.sp ultra-bold, ALL CAPS

**Two-Player Button:**

- Content background: Solid primary magenta (#FF00FF)
- Border: 6.dp solid black (#000000)
- Size: Full width minus 64.dp padding
- Corner radius: 0.dp (sharp corners - pure brutalism)
- Inner padding: 20.dp
- Text positioning: Left-aligned, no center
- Text: ">> TWO PLAYER <<" in 24.sp ultra-bold, white, ALL CAPS
- Shadow: 8.dp black offset shadow (4.dp right, 4.dp down)
- Interactive state: No shadow on press (shadow removed to show interaction)

**AI Section Header:**

- "VS AI - PICK YOUR CHALLENGE" in 16.sp ultra-bold, ALL CAPS, white
- Background: Solid black
- Padding: 12.dp horizontal
- Border-bottom: 2.dp yellow (#FFFF00)

**Difficulty Button Group:**

- Stacked vertically with 0.dp gap (buttons touch)
- All full width

_Easy Button:_

- Background: Lime green (#00FF00)
- Border: 5.dp solid black
- Text: "EASY MODE" ultra-bold, 20.sp, ALL CAPS, black text
- Padding: 18.dp
- Shadow: 6.dp black offset shadow
- Interaction: Remove shadow on press

_Medium Button:_

- Background: Bright cyan (#00FFFF)
- Border: 5.dp solid black
- Text: "MEDIUM CHALLENGE" ultra-bold, 20.sp, ALL CAPS, black text
- Padding: 18.dp
- Shadow: 6.dp black offset shadow

_Hard Button:_

- Background: Red (#FF0000)
- Border: 5.dp solid black
- Text: "IMPOSSIBLE MODE" ultra-bold, 20.sp, ALL CAPS, white text
- Padding: 18.dp
- Shadow: 6.dp black offset shadow

**Responsive Adjustments:**

- Mobile compact: Reduce padding, smaller fonts
- Font size adjustment: Scale down 20% for compact
- Keep maximum width constraints (don't exceed tablet sizing)

---

### Screen 2: Gameplay Screen - Raw & Bold

**Background:**

- Solid black (#000000) primary
- Horizontal dividing lines: 3.dp yellow (#FFFF00) stripes at 20% and 80% of screen
- Right border accent: 8.dp solid magenta (#FF00FF)

**Status Area - Top:**

- Title: "TIC TAC TOE" 40.sp ultra-bold, yellow (#FFFF00), ALL CAPS
- Brutal positioning: Left-aligned with 32.dp margin
- Score display style:
  - Container: Black background with 4.dp yellow border
  - Layout: Side-by-side text blocks
  - "PLAYER X: 5" / "PLAYER O: 3" (ALL CAPS, 18.sp bold)
  - Divider: 3.dp yellow vertical line between scores
  - Padding: 16.dp inside container
  - Corner radius: 0.dp (sharp)
  - Text color: Alternating cyan (#00FFFF) for X, magenta (#FF00FF) for O

**Game Board - Central:**

- 3x3 grid with NO gaps (cells touch)
- Cell styling:
  - Background: Alternating pattern - black and very dark gray (#1A1A1A)
  - Border: 3.dp solid yellow (#FFFF00) on all cells
  - Interior dividing lines: 4.dp yellow separation

_Circle (O) Component:_

- Stroke: 10.dp cyan (#00FFFF)
- No anti-aliasing for raw look (if possible)
- Size: 75% of cell
- Animation: Instant appearance (0ms) for brutalist feel

_Cross (X) Component:_

- Stroke: 10.dp magenta (#FF00FF)
- Heavy, prominent strokes
- Size: 75% of cell
- Animation: Instant appearance

**Bottom Turn Indicator:**

- Position: Below board, full width, black background
- Text: ">>> TURN: PLAYER X <<<"
- Size: 20.sp ultra-bold, ALL CAPS
- Color: Cyan (#00FFFF) animated blinking (500ms pulse)
- Padding: 14.dp
- Border: 2.dp top yellow (#FFFF00), 2.dp bottom yellow
- When AI thinking: "... AI CALCULATING ..." with animated dots

---

### Screen 3: Game Over Bottom Sheet

**Sheet Container:**

- Background: Solid black with 8.dp yellow border on all sides
- Position: Slides from bottom with 0dp corner radius (top corners sharp)
- Padding: 32.dp

**Content - Brutalist Layout:**

- Header: "G A M E O V E R" in 32.sp ultra-bold, spaced letters
- Color: Bright magenta (#FF00FF)
- Text shadow: 2.dp black drop shadow

**Result Box:**

- Background: Yellow (#FFFF00)
- Border: 4.dp solid black
- Text: "PLAYER X WINS!" or "IT'S A DRAW!" in 24.sp ultra-bold, black, ALL CAPS
- Padding: 20.dp
- Margin: 20.dp top and bottom

**Button Layout - Side by Side:**

- Two columns, equal width

_Play Again Button:_

- Background: Lime green (#00FF00)
- Border: 5.dp solid black
- Text: "PLAY AGAIN" ultra-bold, 18.sp, ALL CAPS, black
- Padding: 16.dp
- Height: 64.dp
- No border-radius
- Shadow: 6.dp black

_Exit Button:_

- Background: Red (#FF0000)
- Border: 5.dp solid black
- Text: "EXIT NOW" ultra-bold, 18.sp, ALL CAPS, white
- Padding: 16.dp
- Height: 64.dp
- Shadow: 6.dp black

- Spacing between buttons: 12.dp of black space

---

### Screen 4: Exit Confirmation Bottom Sheet

**Container Design:**

- Black background with 8.dp borders (all sides)
- Yellow (#FFFF00) top border accent (6.dp)
- Padding: 32.dp

**Header - "CONFIRM EXIT":**

- 28.sp ultra-bold, ALL CAPS, cyan (#00FFFF)
- Lettering spacing: Wide tracking

**Confirmation Message Box:**

- Background: Magenta (#FF00FF)
- Border: 4.dp black
- Text: "SURE YOU WANT TO QUIT?" in 20.sp bold, white, ALL CAPS
- Padding: 16.dp
- Margin: 16.dp vertical

**Button Layout - Stacked Full Width:**

_Exit Button:_

- Full width, 64.dp height
- Background: Red (#FF0000)
- Border: 6.dp solid black
- Text: "I'M OUT" ultra-bold, 20.sp, ALL CAPS, white
- Margin-bottom: 12.dp

_Cancel Button:_

- Full width, 64.dp height
- Background: Lime green (#00FF00)
- Border: 6.dp solid black
- Text: "KEEP PLAYING" ultra-bold, 20.sp, ALL CAPS, black
- No margin

---

### Global UI Elements - Neo-Brutalist System

**Primary Color Palette (Maximum Contrast):**

- Primary Yellow: #FFFF00
- Primary Magenta: #FF00FF
- Primary Cyan: #00FFFF
- Primary Lime: #00FF00
- Primary Red: #FF0000
- Background: #000000 (pure black)
- Accent gray: #1A1A1A (dark backgrounds only)
- Text Primary: White (#FFFFFF) or color-dependent
- Text on colors: Black or white for maximum contrast

**Typography - Bold & Aggressive:**

- All weights: Ultra-bold or bold (700-900)
- All quotes: ALL CAPS (except body copy minimally)
- Font: Heavy sans-serif (bold system font)
- Letter spacing: 1-2.sp for titles
- Line-height: Tight (1.0-1.1 for headers)
- No italics, no scripts

**Styling Elements:**

- Border width: 3-8.dp throughout
- All corners: 0.dp (pure rectangles)
- Shadows: 4-8.dp black offset (4.dp right, 4.dp down)
- No gradients
- No transparency effects (except minimal 1-2%)
- Solid color blocking

**Animations:**

- Instant state changes (0-100ms)
- Brutal transitions with no easing curves
- Pulsing effects: 500ms on/off blink
- No spring animations
- Interactive feedback: Shadow removal on press

**Spacing:**

- Large aggressive spacing: 32.dp
- Medium spacing: 20.dp
- Small spacing: 12.dp
- No subtle spacing - all chunky

**Interactive States:**

- Default: Full shadow
- Pressed: Shadow removed, slight color shift
- No hover states (mobile-first)
- Feedback: Immediate visual change

---

## Design Selection Guidance

- **VERSION 1 (Glassmorphism):** Best for modern, premium, tech-forward aesthetic. Most polished and animations-heavy.
- **VERSION 2 (Neumorphism):** Best for professional, clean, minimalist appearance. Sophisticated and calming.
- **VERSION 3 (Neo-Brutalism):** Best for bold, trendy, youth-oriented game. Most eye-catching and memorable.

Each version is complete, holistic, and covers all four screens with comprehensive specifications ready for design tool implementation.
