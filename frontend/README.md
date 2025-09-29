# Frontend

Frontend of Stockinsight

## Installation

Install dependencies

```sh
npm install
```

## Build

Compile and Hot-Reload for Development

```sh
npm run dev
```

Compile and Minify for Production

```sh
npm run build
```

## Linting

Lint with ESLint

```sh
npm run lint
```

Check formatting with Prettier

```sh
npm run check
```

## Testing

Run Unit Tests with Vitest

```sh
npm run test:unit
```

Run End-to-End Tests with Playwright

```sh
# Install browsers for the first run
npx playwright install

# When testing on CI, must build the project first
npm run build

# Runs the end-to-end tests
npm run test:e2e
# Runs the tests only on Chromium
npm run test:e2e -- --project=chromium
# Runs the tests of a specific file
npm run test:e2e -- tests/example.spec.ts
# Runs the tests in debug mode
npm run test:e2e -- --debug
```
