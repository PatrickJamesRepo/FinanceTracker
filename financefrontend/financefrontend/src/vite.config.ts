// vite.config.ts
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
    plugins: [react()],
    server: {
        proxy: {
            // This will proxy requests starting with `/api` to your backend running on localhost:8081.
            '/api': {
                target: 'http://localhost:8081',
                changeOrigin: true,
                secure: false, // adjust if you're using HTTPS
            },
        },
    },
});
