package com.mytrendin.facetracking;

import com.google.android.gms.vision.face.Face;

interface GraphicFaceTracker {
    void onNewItem(int faceId, int faceHappiness, String facePrediction, String faceEmotion, Face item);
}
