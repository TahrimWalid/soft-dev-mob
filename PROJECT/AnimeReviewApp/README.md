# Anime Review App - Project (Planning Phase was polished with AI)

## 📺 Demo Video

**[Anime Reviews App Demo](https://tuni-my.sharepoint.com/:v:/g/personal/walid_tahrim_tuni_fi/IQA0CWEBiYghSpGX1E2Guo_FAflRbrtTtMk59iwXWdFyWaU?e=b03Uzd)** - Watch the app in action on a Samsung Galaxy S21

---

## Features

✅ **LazyColumn List View** - Display all anime reviews with posters, ratings, and favorites
✅ **Multi-Screen Navigation** - Navigate between list, detail, and add review screens  
✅ **Add Review Form** - Title, genre, rating (1-5 slider), review text, favorite toggle
✅ **Detail View** - Full review with large poster image, complete ratings, and review text
✅ **Search & Sort** - Filter by title, sort by rating or alphabetically
✅ **Material3 Theme** - Modern UI with anime-inspired colors (purple & orange)
✅ **Favorite System** - Mark/unmark anime as favorites
✅ **Sample Data** - 10 preloaded anime reviews with full content

## Project skills showcased

   - ✅ Jetpack Compose UI components (Column, Row, Card, LazyColumn)
   - ✅ Material3 theming and styling
   - ✅ Multi-activity navigation with Intents
   - ✅ Compose state management (remember, mutableState, LaunchedEffect)
   - ✅ Custom composable functions (reusable UI)
   - ✅ Resource management (drawable images)
   - ✅ Form handling and validation
   - ✅ Data modeling and sample data
   - ✅ Search/filter and sorting functionality
   - ✅ Interactive UI elements (switches, sliders, buttons)

---

## How to Run the App

### Prerequisites

Before you start, make sure you have:
- **Android SDK** installed (API 36 or higher)
- **Gradle** (included with Android Studio/VS Code)
- **ADB (Android Debug Bridge)** in your system PATH
- **USB cable** to connect your Android phone

### Step 1: Clone the Repository

Open a terminal/command prompt and run:

```bash
git clone https://github.com/TahrimWalid/soft-dev-mob.git
cd soft-dev-mob/PROJECT/AnimeReviewApp
```

### Step 2: Set Up Android SDK Path (Windows)

The project uses the Android SDK. Make sure it's set up:

1. Create or edit `local.properties` in the project root:
   ```
   sdk.dir=C:\\Users\\<YourUsername>\\AppData\\Local\\Android\\Sdk
   ```
   Replace `<YourUsername>` with your Windows username.

2. Verify Android SDK is installed at `C:\Users\<YourUsername>\AppData\Local\Android\Sdk`

### Step 3: Prepare Your Android Phone

#### Enable Developer Options:
1. Go to **Settings** on your phone
2. Scroll down and tap **About Phone**
3. Scroll down to **Build Number**
4. Tap **Build Number** **7 times** (you'll see a countdown: "6 more taps...etc")
5. You'll see a toast message: **"Developer mode enabled"**

#### Enable USB Debugging:
1. Go back to **Settings**
2. Scroll down and find **Developer Options** (now visible)
3. Tap **Developer Options**
4. Toggle **ON** for "USB Debugging"
5. A pop-up will appear asking to allow USB debugging. Tap **OK** or **Allow**

#### Connect Phone to PC:
1. Connect your phone to your PC using a USB cable
2. On your phone screen, you may see a prompt asking "Allow USB debugging?" - Tap **Allow** or **Always allow**
3. Keep the phone unlocked during the installation process

### Step 4: Verify ADB Connection

Open a terminal/command prompt and verify that your phone is recognized:

```bash
adb devices
```

You should see output like:
```
List of attached devices
RFCWB0WL4DR     device
```

**If you see "unauthorized":**
- Check your phone screen - there should be a prompt asking to allow USB debugging
- Tap **Allow** on your phone
- Run `adb devices` again

**If you see nothing:**
- Check if your phone is actually connected
- Try unplugging and plugging back in
- Update ADB: `adb kill-server` then `adb devices`

### Step 5: Build and Install the App

Open a terminal in the project directory and run:

```bash
./gradlew installDebug
```

**On Windows PowerShell, use:**
```powershell
.\gradlew.bat installDebug
```

Wait for the build to complete. You should see:
```
Installed on 1 device.
BUILD SUCCESSFUL in 40s
```

### Step 6: Launch the App

Once installed, you can launch it either:

**Option A: From terminal (ADB):**
```bash
adb shell am start -n com.animereview.app/.MainActivity
```

**Option B: Manually on phone:**
- Look for the app icon (anime-themed) in your phone's app drawer
- Tap to open

### Troubleshooting

**Problem: "gradle command not found" / "./gradlew not recognized"**
- Make sure you're in the correct directory: `cd soft-dev-mob/PROJECT/AnimeReviewApp`
- On Windows PowerShell, use `.\gradlew.bat` instead of `./gradlew`

**Problem: "BUILD FAILED" with Gradle errors**
- Run: `./gradlew clean build`
- This deletes old build files and rebuilds from scratch
- If still failing, check that `local.properties` has the correct SDK path

**Problem: ADB shows "unauthorized"**
- Unplug the phone
- On your phone, go to Settings → Apps and find your file manager
- Clear cache/data (optional)
- Plug phone back in and immediately check for a prompt
- Tap "Allow" to authorize USB debugging

**Problem: "device not found" in ADB**
- Verify USB cable is securely connected
- Try a different USB port on your PC
- On phone, go to Settings → Developer Options and toggle USB Debugging OFF then ON
- Run: `adb kill-server` then `adb devices` to restart ADB

**Problem: App crashes immediately on launch**
- Try: `./gradlew clean installDebug` to do a full rebuild
- Check that all anime poster images are in `app/src/main/res/drawable/`
- The files should be named exactly: `jujutsu_kaisen.jpg`, `demon_slayer.jpg`, etc.

**Problem: Build takes too long / hangs**
- First build is slower (downloads dependencies)
- On slow internet, increase timeout: `./gradlew installDebug --timeout=300`
- Check your internet connection

### Quick Command Reference

```bash
# Check if phone is connected
adb devices

# Restart ADB service
adb kill-server
adb devices

# Build and install
./gradlew installDebug

# Build only (no install)
./gradlew build

# Clean rebuild
./gradlew clean build

# Launch app via ADB
adb shell am start -n com.animereview.app/.MainActivity

# View app logs
adb logcat | grep "animereview"

# Uninstall app
adb uninstall com.animereview.app
```

### App Architecture

Once running, the app has three screens:

1. **MainActivity (List)** - Browse 10 anime reviews
   - Search by title
   - Sort by rating or alphabetically
   - Tap any card to view details
   - Orange FAB (+) button to add review

2. **DetailActivity** - View full anime details
   - Large poster image
   - Full review text
   - Toggle favorite (❤️/🤍)
   - Back button to return to list

3. **AddReviewActivity** - Add a new anime review
   - Title input (required)
   - Genre input
   - Rating slider (1-5 stars)
   - Review text area
   - Favorite toggle
   - Back to return to list

---

**Notes for Teacher/Grader:**
- App uses hardcoded sample data (10 anime reviews)
- No database/persistence - data resets when app closes
- All UI built with Jetpack Compose (no XML layouts)
- Material3 design with custom purple (#6A1B9A) and orange (#FF7043) theme
- Tested on Samsung Galaxy S21 (Android 15)
