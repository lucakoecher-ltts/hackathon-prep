# Angular Application

A minimal Angular application with:
- Axios for backend API calls
- State management via service
- Client-side validation using reactive forms
- Simple page with text field, button, and table

## Features

- **Form Validation**: Required field with minimum length validation
- **API Integration**: Uses axios to fetch data from JSONPlaceholder API
- **State Management**: DataService manages application state
- **Responsive Table**: Displays data in a clean table format

## Getting Started

1. Install dependencies:
   ```bash
   npm install
   ```

2. Start the development server:
   ```bash
   npm start
   ```

3. Open your browser to `http://localhost:4200`

## Usage

- Enter a name in the text field (minimum 2 characters required)
- Click "Add Item" to add the item to the table
- The table initially loads with sample data from the API
- Form validation prevents submission of invalid data

## Architecture

- `AppComponent`: Main component with form and table
- `DataService`: Handles API calls and state management
- Reactive forms for validation
- Axios for HTTP requests
- Standalone components (Angular 17+ style)